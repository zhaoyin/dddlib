package org.dayatang.ioc.spring.factory;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.domain.InstanceProvider;

public class SpringIocUtils {

	private static final ThreadLocal<SpringInstanceProvider> providerHolder = new ThreadLocal<SpringInstanceProvider>();

	private SpringIocUtils() {
		super();
	}

	public static void initInstanceProvider(Class<?>... annotatedClasses) {
		InstanceFactory.setInstanceProvider(getInstanceProvider(annotatedClasses));
	}

	private static InstanceProvider getInstanceProvider(Class<?>[] annotatedClasses) {
		SpringInstanceProvider result = providerHolder.get();
        if (result != null) {
            return result;
        }
        synchronized (SpringIocUtils.class) {
            result = new SpringInstanceProvider(annotatedClasses);
            providerHolder.set(result);
            return result;
        }
	}

	public static void initInstanceProvider(String... acFiles) {
		InstanceFactory.setInstanceProvider(getInstanceProvider(acFiles));
	}

	private static InstanceProvider getInstanceProvider(String... acFiles) {
		SpringInstanceProvider result = providerHolder.get();
        if (result != null) {
            return result;
        }
        synchronized (SpringIocUtils.class) {
			result = new SpringInstanceProvider(acFiles);
			providerHolder.set(result);
            return result;
		}
	}

}
