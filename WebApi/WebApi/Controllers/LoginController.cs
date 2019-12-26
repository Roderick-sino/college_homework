using System.Data.SqlClient;
using System.Web.Http;
using WebApi.Models;

namespace ClassSchedule.Controllers
{
    public class LoginController : ApiController
    {
        public object Post([FromBody]Student stu)
        {
            SqlConnection sqlconn = new SqlConnection("Data Source=127.0.0.1;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlconn.Open();
            string sql = "select * from student where s_id='" + stu.xh + "'and password='" + stu.mm + "'";
            SqlCommand cmd = new SqlCommand(sql, sqlconn);
            SqlDataReader dr = cmd.ExecuteReader();
            if (dr.Read())
            {
                return new { msg = "success" };

            }
            else
            {
                return new { msg = "false" };
            }
            dr.Close();
            sqlconn.Close();
        }
    }
}