using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebApi.Models;

namespace WebApi.Controllers
{
    public class RegisterController : ApiController
    {
        public object Post([FromBody]Student stu)
        {
            SqlConnection sqlconn = new SqlConnection("Data Source=127.0.0.1;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlconn.Open();
            string sql = "insert into student values('" + stu.xh + "','" + stu.mm + "')";
            SqlCommand cmd = new SqlCommand(sql, sqlconn);
            int res = cmd.ExecuteNonQuery();//返回受影响行数
            sqlconn.Close();
            if (res > 0)
            {
                return new
                {
                    code = 200,
                    msg = "success"
                };
            }
            else
            {
                return new { msg = "false" };
            }
        }
    }
}