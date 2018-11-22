package com.nd.me.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import sun.util.ResourceBundleEnumeration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @Author
 * @Description
 * @Date Create in 上午 8:59 2018/11/19 0019
 */
public class I18NProvider {
    private static I18NProvider provider = new I18NProvider();

    public I18NProvider() {
    }

    public static void setProvider(com.nd.gaea.I18NProvider provider) {
        provider = provider;
    }

    public static I18NProvider getProvider() {
        return provider;
    }

    public static String getString(String name) {
        Assert.hasText(name);
        if (name.startsWith("resource:")) {
            name = name.substring(9).trim();
        }

        ResourceBundle resourceBundle = provider.getResourceBundle();
        return resourceBundle.containsKey(name) ? resourceBundle.getString(name) : name;
    }

    public static String getString(Locale locale, String name) {
        Assert.hasText(name);
        Assert.notNull(locale);
        if (name.startsWith("resource:")) {
            name = name.substring(9).trim();
        }

        ResourceBundle resourceBundle = provider.getResourceBundle(locale);
        return resourceBundle.containsKey(name) ? resourceBundle.getString(name) : name;
    }

    public static boolean containsKey(String name) {
        return provider.getResourceBundle().containsKey(name);
    }

    public static boolean containsKey(Locale locale, String name) {
        return provider.getResourceBundle(locale).containsKey(name);
    }

    public ResourceBundle getResourceBundle() {
        Locale locale = this.getDefaultLocale();
        MultiControl multiControl = new MultiControl();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("waf_resource", locale, multiControl);
        return resourceBundle;
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle("waf_resource", locale, new MultiControl());
    }

    protected Locale getDefaultLocale() {
        return LocaleContextHolder.getLocale();
    }

    private static class MultiResourcePropertyResourceBundle extends ResourceBundle {
        private final HashMap lookup = new HashMap();

        public MultiResourcePropertyResourceBundle(List<URL> urls) throws IOException {
            Iterator i$ = urls.iterator();

            while(i$.hasNext()) {
                URL url = (URL)i$.next();
                URLConnection urlc = url.openConnection();
                InputStream is = urlc.getInputStream();

                try {
                    Properties temp = new Properties();
                    temp.load(is);
                    this.lookup.putAll(temp);
                } finally {
                    is.close();
                }
            }

        }

        protected Object handleGetObject(String key) {
            if (key == null) {
                throw new NullPointerException();
            } else {
                return this.lookup.get(key);
            }
        }

        public Enumeration<String> getKeys() {
            ResourceBundle parent = this.parent;
            return new ResourceBundleEnumeration(this.lookup.keySet(), parent != null ? parent.getKeys() : null);
        }

        protected Set<String> handleKeySet() {
            return this.lookup.keySet();
        }
    }

    private static class MultiControl extends ResourceBundle.Control {
        private MultiControl() {
        }

        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            String bundleName = this.toBundleName(baseName, locale);
            String resourceName = this.toResourceName(bundleName, "properties");
            List<URL> resources = Collections.list(loader.getResources(resourceName));
            Collections.reverse(resources);
            return new MultiResourcePropertyResourceBundle(resources);
        }
    }
}