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
package org.eclipse.rcptt.ui.actions;

import java.util.Set;

import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.model.ITestCase;
import org.eclipse.rcptt.core.model.search.Q7SearchCore;
import org.eclipse.rcptt.internal.ui.Messages;

public class OpenScenarioAction extends OpenNamedElementAction implements
		IWorkbenchWindowActionDelegate {
	protected String getWindowTitle() {
		return Messages.OpenScenarioAction_WindowTitle;
	}

	protected void fillNamedElements(Set<IQ7NamedElement> allElements) {
		IQ7NamedElement[] elements = Q7SearchCore.findAllElements();
		for (IQ7NamedElement iq7NamedElement : elements) {
			if( iq7NamedElement instanceof ITestCase) {
				allElements.add(iq7NamedElement);
			}
		}
	}
}
