package org.web.configuration;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerFilters(servletContext);
    }*/

    private void registerFilters(ServletContext servletContext) {
        DelegatingFilterProxy usersFilter = new DelegatingFilterProxy();
        usersFilter.setTargetBeanName("usersFilter");
        servletContext.addFilter("usersFilter", usersFilter.getClass()).
                addMappingForUrlPatterns(null,false,"/user/list");
    }

}
