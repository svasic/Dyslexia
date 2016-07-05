package si.psi.dyslexia.core;

import si.psi.dyslexia.PsiApplication;

/**
 * Created by SVasic on 26.8.2015.
 */
public interface InstanceComponent<T, U extends PsiApplication> {

    T getInstance(U psiApplication);
    T getInstance();



}
