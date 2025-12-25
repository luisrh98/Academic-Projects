package es.iessoterohernandez.daw.endes.boletinJUnit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@Suite
@SelectPackages("es.iessoterohernandez.daw.endes.boletinJUnit")
public class AllTest {
    // Esta clase está vacía, actúa solo como marcador para la suite de pruebas.
}