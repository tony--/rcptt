/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ui.wizards.plain;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class Q7PortableFormatImport extends Wizard implements IImportWizard {
	private Q7PortableFormatImportPage page;
	private IContainer initialContainer = null;

	public Q7PortableFormatImport() {
		setWindowTitle("Import");
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		Object object = selection.getFirstElement();
		if (object != null && object instanceof IContainer) {
			initialContainer = (IContainer) object;
		} else if (object != null && object instanceof IResource) {
			initialContainer = ((IResource) object).getParent();
		}
	}

	@Override
	public void addPages() {
		page = new Q7PortableFormatImportPage("RCPTT Portable Import page",
				initialContainer);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		if (page.isPageComplete()) {
			return page.finish();
		}
		return false;
	}
}
