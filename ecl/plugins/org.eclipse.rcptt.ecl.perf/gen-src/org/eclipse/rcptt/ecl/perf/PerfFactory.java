/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.rcptt.ecl.perf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.rcptt.ecl.perf.PerfPackage
 * @generated
 */
public interface PerfFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PerfFactory eINSTANCE = org.eclipse.rcptt.ecl.perf.impl.PerfFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Counter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Counter</em>'.
	 * @generated
	 */
	PerfCounter createPerfCounter();

	/**
	 * Returns a new object of class '<em>Start Time Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Time Measure</em>'.
	 * @generated
	 */
	StartTimeMeasure createStartTimeMeasure();

	/**
	 * Returns a new object of class '<em>Stop Time Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stop Time Measure</em>'.
	 * @generated
	 */
	StopTimeMeasure createStopTimeMeasure();

	/**
	 * Returns a new object of class '<em>Measure Time</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Time</em>'.
	 * @generated
	 */
	MeasureTime createMeasureTime();

	/**
	 * Returns a new object of class '<em>Constrain Max</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constrain Max</em>'.
	 * @generated
	 */
	ConstrainMax createConstrainMax();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PerfPackage getPerfPackage();

} //PerfFactory
