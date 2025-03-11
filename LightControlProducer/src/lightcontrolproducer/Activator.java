package lightcontrolproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting Light Control Service...");

        LightControlService lightControlService = new LightControlServiceImpl();
        serviceRegistration = bundleContext.registerService(LightControlService.class.getName(), lightControlService, null);

        System.out.println("Light Control Service started.");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping Light Control Service...");

        serviceRegistration.unregister();

        System.out.println("Light Control Service stopped.");
    }

}
