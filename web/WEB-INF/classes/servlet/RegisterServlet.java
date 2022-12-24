package servlet;

//导入库
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//导入user相关
import Dao.UserDao;
import domain.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //先判断信息是否为空
        if(username==null || password==null || email==null || username.trim().isEmpty() ||password.trim().isEmpty()|| email.trim().isEmpty()) {
            //用户名为空
            System.out.println("信息为空");

//            request.getRequestDispatcher("/client/login_fail.jsp").forward(request,response);
            //取response的输出流
            PrintWriter out = response.getWriter();
            //输出到下一个页面
            out.println("信息为空，创建失败！请跳转注册界面重新注册！");
        }
        else {
            //User user = new UserDao().findUser(username);
            //addUser是boolean类型，不能和findUser一样直接创建一个新的user
            UserDao u = new UserDao();
            //用res承接addUser的返回结果
            boolean res = u.addUser(username, password, email);
            if (res) {
                //若创建成功则跳转
                System.out.println("注册成功");
                //response.sendRedirect("index.jsp");


                //取response的输出流
                PrintWriter out = response.getWriter();
                //输出到下一个页面
                out.println("注册成功，欢迎");
                out.println(username);

            } else {
                //创建失败：因为username是主键，重复了

                System.out.println("用户名重复");
                //取response的输出流
                PrintWriter out = response.getWriter();
                //输出到下一个页面
                out.println("用户名已被占用，请更改用户名重新注册！");
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
