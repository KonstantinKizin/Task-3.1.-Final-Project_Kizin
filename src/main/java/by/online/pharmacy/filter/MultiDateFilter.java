package by.online.pharmacy.filter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
;

public class MultiDateFilter implements Filter {

    private DiskFileItemFactory factory = new DiskFileItemFactory();
    private ServletFileUpload upload = new ServletFileUpload(factory);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            List<FileItem> fileItems = upload.parseRequest((HttpServletRequest) servletRequest);

            Iterator<FileItem> iterator = fileItems.iterator();

            while (iterator.hasNext()){
                FileItem fi = iterator.next();
                if(fi.isFormField()){
                    servletRequest.setAttribute(fi.getFieldName(),fi.getString("utf-8"));
                }else {
                    servletRequest.setAttribute(fi.getFieldName(),fi.get());
                }
            }

        } catch (FileUploadException e) {
            throw new ServletException(e);
        }finally {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        factory = null;
        upload = null;
    }
}
