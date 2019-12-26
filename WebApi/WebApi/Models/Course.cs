using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApi.Models
{
    public class Course
    {
        public string xh  { get; set; }
        public string kch { get; set; }     //课程号
        public string kcm { get; set; }     //课程名
        public float xf { get; set; }       //学分
        public string ls { get; set; }      //老师   
        public int d_o_w { get; set; }      //day of week
        public int c_o_d { get; set; }      //class of day
        public int s_o_d { get; set; }      //single or double
        public int s_w { get; set; }        //start_week
        public int e_w { get; set; }        //end_week
        public string js { get; set; }      //教室
    }
}