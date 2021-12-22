package org.campus.webshop.web.servlet;

import org.campus.webshop.entity.Product;
import org.campus.webshop.service.ProductService;
import org.campus.webshop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllReviewsRequestServlet extends HttpServlet {
    private ProductService productService;

    public ShowAllReviewsRequestServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap();
        parameters.put("products", products);
        String page = pageGenerator.getPage("product_list.html", parameters);
//        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(page);
    }
}
