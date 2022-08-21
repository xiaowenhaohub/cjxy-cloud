package com.changji.cloud.website.utils;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.website.model.Course;
import com.changji.cloud.website.model.StudentInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 19:55 2022/8/21
 * @ Modified By：
 */
public class HTMLUtil {

    /**
     * 一二节
     */
    private static final String ONE_TWO = "003D6B98F16A4D5AB56A88E531818B69-";

    /**
     * 三四节
     */
    private static final String THREE_FOUR = "71142B2A52FE4F66A743863A77108F21-";

    /**
     * 五六节
     */
    private static final String FIVE_SIX = "45E95DE465044957AAAC9645782B0F19-";

    /**
     * 七八节
     */
    private static final String SEVEN_EIGHT = "FC4B748390014E92AC4C3B0ACE70D9A4-";

    /**
     * 九十节
     */
    private static final String NINE_TEN = "E64AA0AE1AD542F992B291284B3FB263-";

    /**
     *  last id
     */
    private static final String END_ID = "-2";



    public static AuthAccountVO getStudentInfo(String context) {

        Document document = Jsoup.parse(context);
        Element tr3 = getElementBySeq(document, "tr", 2);
        Element tr4 = getElementBySeq(document, "tr", 3);
        String institute = getElementBySeq(tr3, "td", 0).text().replaceFirst("院系：","");
        String specialty = getElementBySeq(tr3, "td", 1).text().replaceFirst("专业：","");
        String classes = getElementBySeq(tr3, "td", 3).text().replaceFirst("班级：信息","");
        String name = getElementBySeq(tr4, "td", 1).text();

        AuthAccountVO authAccountVO = new AuthAccountVO();
        authAccountVO.setInstitute(institute);
        authAccountVO.setSpecialty(specialty);
        authAccountVO.setClasses(classes);
        authAccountVO.setStudentName(name);

        return authAccountVO;
    }



    public static List<List<Course>> getCourseList(String context) {
        List<List<Course>> courseList = new ArrayList<>();
        Document document = Jsoup.parse(context);

        Element tbody = document.select("tbody").first();
        Elements trs = tbody.select("tr");
        for (int i = 1; i < 6; i ++) {
            List<Course> courseRow = getCourseRow(trs.get(i));
            courseList.add(courseRow);
        }
        return courseList;
    }

    /**
     * 获取一行共七天的课程
     * @param tr
     * @return
     */
    public static List<Course> getCourseRow(Element tr){
        List<Course> coursesRow = new ArrayList<>();
        Elements tds = tr.select("td");
        for (int i  = 0 ; i < 7; i ++){
            coursesRow.add(getCourse(tds.get(i)));
        }
        return coursesRow;
    }

    /**
     * 获取单节课程所有信息
     * @param td
     * @return
     */
    public static Course getCourse(Element td) {

        Course course = new Course();
        Element div = td.select("div").get(1);
        String[] split = div.text().split(" ");
        if(split.length == 1){
            course.setCourseName("");
            course.setTeacherName("");
            course.setWeekly("");
            course.setCourseRoom("");
        }else {
            course.setCourseName(split[0]);
            course.setTeacherName(split[1]);
            course.setWeekly(split[2]);
            course.setCourseRoom(split[3]);
        }
        return course;
    }

    /**
     * 通过id获取文本内容
     * @param element
     * @param id
     * @return
     */
    public static String getContextById(Element element, String id) {
        try {
            return element.getElementById(id).text();
        } catch (Exception e) {
            throw new ServiceException("html id不存在");
        }

    }

    /**
     * 更具序号选择element
     * @param element
     * @param table
     * @param seq
     * @return
     */
    public static Element getElementBySeq(Element element,String table, Integer seq) {
        return element.select(table).get(seq);
    }


//    public static List<List<Course>> getCourseList(String context) {
//        List<List<Course>> courseList = new ArrayList<>();
//        Document document = Jsoup.parse(context);
//
//        Element tbody = document.select("tbody").first();
//        Elements tr = tbody.select("tr");
//        for (int i = 1; i < 6; i ++) {
//            tr.get(i);
//        }
//
//        courseList.add(getCourseRow(document, ONE_TWO));
//        courseList.add(getCourseRow(document, THREE_FOUR));
//        courseList.add(getCourseRow(document, FIVE_SIX));
//        courseList.add(getCourseRow(document, SEVEN_EIGHT));
//        courseList.add(getCourseRow(document, NINE_TEN));
//        return courseList;
//    }
//
//    /**
//     * 获取一行共七天的课程
//     * @param element
//     * @param id
//     * @return
//     */
//    public static List<Course> getCourseRow(Element element, String id){
//        List<Course> coursesRow = new ArrayList<>();
//        for (int i  = 1 ; i < 8; i ++){
//            Course course = getCourseById(element, id + i + "-2");
//            coursesRow.add(course);
//        }
//        return coursesRow;
//    }
//
//    /**
//     * 获取单节课程所有信息
//     * @param element
//     * @param id
//     * @return
//     */
//    public static Course getCourseById(Element element, String id) {
//        Course course = new Course();
//        String context = getContextById(element, id);
//        String[] split = context.split(" ");
//        if(split.length == 1){
//            course = null;
//        }else {
//            course.setCourseName(split[0]);
//            course.setTeacherName(split[1]);
//            course.setWeekly(split[2]);
//            course.setCourseRoom(split[3]);
//        }
//        return course;
//    }
//
//    /**
//     * 通过id获取文本内容
//     * @param element
//     * @param id
//     * @return
//     */
//    public static String getContextById(Element element, String id) {
//        try {
//            return element.getElementById(id).text();
//        } catch (Exception e) {
//            throw new ServiceException("html id不存在");
//        }
//
//    }

}
