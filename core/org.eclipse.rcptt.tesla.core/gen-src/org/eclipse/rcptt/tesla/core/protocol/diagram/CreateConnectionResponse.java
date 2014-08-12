/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rcptt.tesla.core.protocol.diagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.rcptt.tesla.core.protocol.raw.Element;
import org.eclipse.rcptt.tesla.core.protocol.raw.Response;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Create Connection Response</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.rcptt.tesla.core.protocol.diagram.CreateConnectionResponse#getFigure <em>Figure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.rcptt.tesla.core.protocol.diagram.DiagramPackage#getCreateConnectionResponse()
 * @model
 * @generated
 */
public interface CreateConnectionResponse extends Response {
	/**
	 * Returns the value of the '<em><b>Figure</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.rcptt.tesla.core.protocol.raw.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Figure</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Figure</em>' containment reference list.
	 * @see org.eclipse.rcptt.tesla.core.protocol.diagram.DiagramPackage#getCreateConnectionResponse_Figure()
	 * @model containment="true"
	 * @generated
	 */
	EList<Element> getFigure();

} // CreateConnectionResponse
