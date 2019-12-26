using System.Data;
using System.Data.SqlClient;
using System.Web.Http;
using WebApi.Models;

namespace WebApi.Controllers
{
    public class LessonController : ApiController
    {

        //搜寻所有的课程
        [Route("api/select/course")]
        [HttpGet]
        public object allcourse()
        {
            string sql = "select course_id as '课程id' ,ccourse_name as '课程名' ,sore as '学分',teacher as '老师',lesson as '教室名' ,day_of_week as '星期',class_of_day as '节课',single_or_double as '单双周',start_week as '起始周',end_week as '结束周' from course";
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            DataSet dataSet = new DataSet();
            SqlDataAdapter sqlAdapter = new SqlDataAdapter(sql, sqlConnection);
            sqlAdapter.Fill(dataSet);
            sqlConnection.Close();
            return new
            {
                msg = "success",
                list = dataSet.Tables[0]
            };


        }



        //搜寻所有的课程
        [Route("api/select/allcourse")]
        [HttpPost]
        public object selectall([FromBody]Student stu)
        {
            string ans = "";
            string sql1 = "select * from student where s_id='" + stu.xh + "'and password='" + stu.mm + "'";
            string sql = "select  course.course_id  ,ccourse_name  ,sore ,teacher ,lesson   ,day_of_week  ,class_of_day  ,single_or_double  ,start_week ,end_week from course,class where class.student_id='" + stu.xh + "' and class.course_id=course.course_id";
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            SqlCommand cmd = new SqlCommand(sql1, sqlConnection);
            SqlDataReader dr = cmd.ExecuteReader();
            
            if (dr.Read())
            {
               ans = "success";
            }
            else
            {
               ans = "false";
            }
            sqlConnection.Close();
            sqlConnection.Open();
            DataSet dataSet = new DataSet();
            SqlDataAdapter sqlAdapter = new SqlDataAdapter(sql, sqlConnection);

            sqlAdapter.Fill(dataSet);
            sqlConnection.Close();

                return new { msg = ans,
                 alist = dataSet.Tables[0]
                };

          
        }
        //input 学号 output 课程所有信息
        [Route("api/lesson/select/{xuehao}")]
        [HttpGet]
        public object selectuser(string xuehao)
        {
            string sql = "select course_id as '课程id' ,ccourse_name as '课程名' ,sore as '学分',teacher as '老师',lesson as '教室名' ,day_of_week as '星期',class_of_day as '节课',single_or_double as '单双周',start_week as '起始周',end_week as '结束周'  from course,student where s_id ='" + xuehao + "'";
            //select course_id as '课程id' ,ccourse_name as '课程名' ,sore as '学分',teacher as '老师',lesson as '教室名' ,day_of_week as '星期',class_of_day as '节课',single_or_double as '单双周',start_week as '起始周',end_week as '结束周'  from course,student where s_id ='1620295'
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            DataSet dataSet = new DataSet();
            SqlDataAdapter sqlAdapter = new SqlDataAdapter(sql, sqlConnection);
            sqlAdapter.Fill(dataSet);
            string result = "";
            for (int i = 0; i < dataSet.Tables[0].Rows.Count; i++)
            {
                DataRow dr = dataSet.Tables[0].Rows[i];
                string course_id = dr["课程id"].ToString();
                string course_name = dr["课程名"].ToString();
                string sore = dr["学分"].ToString();
                string teacher = dr["老师"].ToString();
                string lesson = dr["教室名"].ToString();
                string day_of_week = dr["星期"].ToString();
                string class_of_day = dr["节课"].ToString();
                string sing_or_double = dr["单双周"].ToString();
                string start_week = dr["起始周"].ToString();
                string end_week = dr["结束周"].ToString();
                result += course_id + "," + course_name + "," + sore + "," + teacher + ","
               + lesson + "," + day_of_week + "," + class_of_day + "," + sing_or_double + "," + start_week + "," + end_week + "|";
            }
            return dataSet.Tables[0];
        }

        //修改密码
        [Route("api/lesson/password")]
        [HttpPost]

        public object editmm([FromBody] Student stu)
        {
            string sql = "update student set password='" + stu.mm + "' where s_id='" + stu.xh + "'";
            //  update student set password='123456' where s_id='1620295'
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            SqlCommand cmd = new SqlCommand(sql, sqlConnection);
            int res = cmd.ExecuteNonQuery();
            sqlConnection.Close();
            if (res > 0)
            {
                return new
                {
                    success = true
                };
            }
            else
            {
                return new
                {
                    success = false
                };
            }
        }

        //添加课程
        [Route("api/course/insert")]
        [HttpPost]
        public object addition([FromBody]Course1 c1)
        {
            string sql = "insert into course(course_id,ccourse_name,sore,teacher,day_of_week,class_of_day,single_or_double,start_week,end_week,lesson) values('" + c1.kch + "','" + c1.kcm + "'," + c1.xf + ",'" + c1.ls + "'," + c1.d_o_w + "," + c1.c_o_d + "," + c1.s_o_d + "," + c1.s_w + "," + c1.e_w + ",'" + c1.js + "')";
            //insert into course(course_id,ccourse_name,sore,teacher,day_of_week,class_of_day,single_or_double,start_week,end_week,lesson) values('2050554','java',2.0,'yyf',1,1,1,2,13,'3教303')
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            SqlCommand cmd = new SqlCommand(sql, sqlConnection);
            int res = cmd.ExecuteNonQuery();
            sqlConnection.Close();
            if (res > 0)
            {
                return new { msg = "success" };
            }
            else
            {
                return new { msg = "false" };
            }
        }


        //添加学生上课信息
        [Route("api/student/insert")]
        [HttpPost]
        public object addition([FromBody]Class1 c)
        {
            string sql = "insert into class values('" + c.xh + "','" + c.kch + "')";

            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            SqlCommand cmd = new SqlCommand(sql, sqlConnection);
            int res = cmd.ExecuteNonQuery();
            sqlConnection.Close();
            if (res > 0)
            {
                return new { msg = "success" };
            }
            else
            {
                return new { msg = "false" };
            }
        }

        //删除学生上课信息
        [Route("api/student/delete")]
        [HttpPost]
        public object cut_out([FromBody]Class1 c)
        {
            string sql = "delete from class where student_id='" + c.xh + "' and course_id='" + c.kch + "'";
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            SqlCommand cmd = new SqlCommand(sql, sqlConnection);
            int res = cmd.ExecuteNonQuery();
            sqlConnection.Close();
            if (res > 0)
            {
                return new { msg = "success" };
            }
            else
            {
                return new { msg = "false" };
            }
        }

        //查询学生上课信息
        [Route("api/student/scourse")]
        [HttpPost]
        public object select_student_course([FromBody]Course c)
        {
            string sql = "select course.course_id as '课程id' ,ccourse_name as '课程名' ,sore as '学分',teacher as '老师',lesson as '教室名' ,day_of_week as '星期',class_of_day as '节课',single_or_double as '单双周',start_week as '起始周',end_week as '结束周'  from course,class where student_id='"+c.xh+"' and day_of_week = "+ c.d_o_w +" and class_of_day = "+ c.c_o_d +" and course.course_id=class.course_id";
            //select course.course_id as '课程id' ,ccourse_name as '课程名' ,sore as '学分',teacher as '老师',lesson as '教室名' ,day_of_week as '星期',class_of_day as '节课',single_or_double as '单双周',start_week as '起始周',end_week as '结束周'  from course,class where student_id=1620295 and day_of_week = 5 and class_of_day = 1 and course.course_id=class.course_id
            SqlConnection sqlConnection = new SqlConnection("Data Source=.;Initial Catalog=Class_table;Integrated Security=False;User Id=sa;Password=123456");
            sqlConnection.Open();
            DataSet dataSet = new DataSet();
            SqlDataAdapter sqlAdapter = new SqlDataAdapter(sql, sqlConnection);
            sqlAdapter.Fill(dataSet);
            sqlConnection.Close();
            return new
            {
                msg = "success",
                alist = dataSet.Tables[0]
            };
        }

    }
}
