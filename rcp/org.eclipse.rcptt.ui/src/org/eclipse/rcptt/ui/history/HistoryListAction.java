/*******************************************************************************
 *  Copyright (c) 2000, 2014 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.rcptt.ui.history;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.rcptt.internal.ui.Messages;
import org.eclipse.rcptt.internal.ui.Q7UIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

class HistoryListAction extends Action {

	private class HistoryListDialog extends StatusDialog {
		private static final int MAX_MAX_ENTRIES = 100;
		private ListDialogField fHistoryList;
		private StringDialogField fMaxEntriesField;
		private int fMaxEntries;

		private Object fResult;

		private HistoryListDialog() {
			super(fHistory.getShell());
			setTitle(fHistory.getHistoryListDialogTitle());

			createHistoryList();
			createMaxEntriesField();
			setHelpAvailable(false);
		}

		protected boolean isResizable() {
			return true;
		}

		private void createHistoryList() {
			IListAdapter adapter = new IListAdapter() {
				public void customButtonPressed(ListDialogField field, int index) {
					doCustomButtonPressed(index);
				}

				public void selectionChanged(ListDialogField field) {
					doSelectionChanged();
				}

				public void doubleClicked(ListDialogField field) {
					doDoubleClicked();
				}
			};
			String[] buttonLabels = new String[] { Messages.HistoryListAction_RemoveButton, Messages.HistoryListAction_RemoveAllButton };
			LabelProvider labelProvider = new TestRunLabelProvider();
			fHistoryList = new ListDialogField(adapter, buttonLabels,
					labelProvider);
			fHistoryList.setLabelText(fHistory.getHistoryListDialogMessage());

			List<?> historyEntries = fHistory.getHistoryEntries();
			fHistoryList.setElements(historyEntries);

			Object currentEntry = fHistory.getCurrentEntry();
			ISelection sel;
			if (currentEntry != null) {
				sel = new StructuredSelection(currentEntry);
			} else {
				sel = new StructuredSelection();
			}
			fHistoryList.selectElements(sel);
		}

		private void createMaxEntriesField() {
			fMaxEntriesField = new StringDialogField();
			fMaxEntriesField.setLabelText(fHistory.getMaxEntriesMessage());
			fMaxEntriesField.setDialogFieldListener(new IDialogFieldListener() {

				public void dialogFieldChanged(DialogField field) {
					String maxString = fMaxEntriesField.getText();
					boolean valid;
					try {
						fMaxEntries = Integer.parseInt(maxString);
						valid = fMaxEntries > 0
								&& fMaxEntries < MAX_MAX_ENTRIES;
					} catch (NumberFormatException e) {
						valid = false;
					}
					if (valid)
						updateStatus(new Status(IStatus.OK,
								Q7UIPlugin.PLUGIN_ID, null));
					else
						updateStatus(new Status(
								IStatus.ERROR,
								Q7UIPlugin.PLUGIN_ID,
								MessageFormat
										.format(
												Messages.HistoryListAction_OutOfBoundsErrorMsg,
												Integer
														.toString(MAX_MAX_ENTRIES))));
				}
			});
			fMaxEntriesField
					.setText(Integer.toString(fHistory.getMaxEntries()));
		}

		protected Control createDialogArea(Composite parent) {
			initializeDialogUnits(parent);

			Composite composite = (Composite) super.createDialogArea(parent);

			Composite inner = new Composite(composite, SWT.NONE);
			inner.setLayoutData(new GridData(GridData.FILL_BOTH));
			inner.setFont(composite.getFont());

			LayoutUtil.doDefaultLayout(inner, new DialogField[] { fHistoryList,
					new Separator() }, true);
			LayoutUtil.setHeightHint(fHistoryList.getListControl(null),
					convertHeightInCharsToPixels(12));
			LayoutUtil.setHorizontalGrabbing(fHistoryList.getListControl(null));

			Composite additionalControls = new Composite(inner, SWT.NONE);
			additionalControls.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
					true, false));
			LayoutUtil.doDefaultLayout(additionalControls,
					new DialogField[] { fMaxEntriesField }, false);
			LayoutUtil.setHorizontalGrabbing(fMaxEntriesField
					.getTextControl(null));

			applyDialogFont(composite);
			return composite;
		}

		private void doCustomButtonPressed(int index) {
			switch (index) {
			case 0: // remove
				fHistoryList.removeElements(fHistoryList.getSelectedElements());
				fHistoryList.selectFirstElement();
				break;

			case 1: // remove all
				fHistoryList.removeAllElements();
				break;
			default:
				break;
			}
		}

		private void doDoubleClicked() {
			okPressed();
		}

		private void doSelectionChanged() {
			List<?> selected = fHistoryList.getSelectedElements();
			if (selected.size() >= 1) {
				fResult = selected.get(0);
			} else {
				fResult = null;
			}
			fHistoryList.enableButton(0, selected.size() != 0);
		}

		public Object getResult() {
			return fResult;
		}

		public List<?> getRemaining() {
			return fHistoryList.getElements();
		}

		public int getMaxEntries() {
			return fMaxEntries;
		}

	}

	private final class TestRunLabelProvider extends LabelProvider {
		private final HashMap<ImageDescriptor, Image> fImages = new HashMap<ImageDescriptor, Image>();

		public String getText(Object element) {
			return fHistory.getText(element);
		}

		public Image getImage(Object element) {
			ImageDescriptor imageDescriptor = fHistory
					.getImageDescriptor(element);
			return getCachedImage(imageDescriptor);
		}

		private Image getCachedImage(ImageDescriptor imageDescriptor) {
			Object cached = fImages.get(imageDescriptor);
			if (cached != null)
				return (Image) cached;
			Image image = imageDescriptor.createImage(fHistory.getShell()
					.getDisplay());
			fImages.put(imageDescriptor, image);
			return image;
		}

		public void dispose() {
			for (Iterator<Image> iter = fImages.values().iterator(); iter
					.hasNext();) {
				Image image = iter.next();
				image.dispose();
			}
			fImages.clear();
		}
	}

	private ViewHistory fHistory;

	public HistoryListAction(ViewHistory history) {
		super(null, IAction.AS_RADIO_BUTTON);
		fHistory = history;
		fHistory.configureHistoryListAction(this);
	}

	public void run() {
		HistoryListDialog dialog = new HistoryListDialog();
		if (dialog.open() == Window.OK) {
			fHistory.setHistoryEntries(dialog.getRemaining(), dialog
					.getResult());
			fHistory.setMaxEntries(dialog.getMaxEntries());
		}
	}
}
