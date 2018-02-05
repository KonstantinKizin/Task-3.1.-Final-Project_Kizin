package by.online.pharmacy.filter;

import by.online.pharmacy.controller.constant.ControllerConstant.ProductProperty;
import by.online.pharmacy.service.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberModifier;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MultiDateFilterTest {


    @Mock
    private ServletFileUpload upload;

    @Mock
    private List<FileItem> fileItems;

    @Mock
    private Iterator<FileItem> iterator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private FileItem fileItem;

    private MultiDateFilter filter;


    @Before
    public  void setUpClass() throws IllegalAccessException, ServiceException, FileUploadException {

        MockitoAnnotations.initMocks(this);
        filter = new MultiDateFilter();
        MemberModifier.field(MultiDateFilter.class,"upload").set(filter,upload);
        when(upload.parseRequest(request)).thenReturn(fileItems);
        when(fileItems.iterator()).thenReturn(iterator);
    }



    @Test
    public void when_come_empty_request_attributes_test() throws IOException, ServletException {

        //given
        when(iterator.hasNext()).thenReturn(false);

        //when
        filter.doFilter(request,response,filterChain);

        //then
        verifyNoMoreInteractions(request,response);
        verify(filterChain).doFilter(request,response);

    }

    @Test
    public void when_come_request_attributes_with_byte_arr_test() throws IOException, ServletException {

        //given
        when(iterator.hasNext()).thenReturn(true,false);
        when(iterator.next()).thenReturn(fileItem);
        when(fileItem.isInMemory()).thenReturn(true);
        when(fileItem.getFieldName()).thenReturn(ProductProperty.IMAGE_PARAMETER);

        //when
        filter.doFilter(request,response,filterChain);

        //then
        verify(request).setAttribute(anyString(),anyObject());
        verifyNoMoreInteractions(request);
        verify(filterChain).doFilter(request,response);
    }




}
