package com.changji.cloud.website.utils;

import com.changji.cloud.api.website.vo.AuthAccountVO;
import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.utils.StringUtils;
import com.changji.cloud.website.model.Lesson;
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
public class JsoupUtil {




    /**
     * 对官网html进行解析获取用户信息
     * @param context
     * @return
     */
    public static AuthAccountVO getStudentInfo(String context) {

        Element document = parse(context);
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
        authAccountVO.setRealName(name);
        return authAccountVO;
    }

    /**
     * 获取课程信息
     * @param context
     * @return
     */
    public static List<List<Lesson>> getCourseList(String context) {
        List<List<Lesson>> courseList = new ArrayList<>();
        Element tbody = getElementByLabel(context, "tbody");

        if (tbody == null) {
            throw  new ServiceException("未查询到该班级课表，请核对班级信息后查询");
        }

        for (int i = 1; i < 6; i ++) {
            List<Lesson> listRow = new ArrayList<>();
            for (int j = 0; j < 7; j ++) {
                Element td = getElementBySeq(tbody, "td", i + j * 5);
                listRow.add(lesson(td));
            }
            courseList.add(listRow);
        }

        return courseList;
    }

    /**
     * 封装课信息
     * @param element
     * @return
     */
    public static Lesson lesson(Element element) {
        String[] split = getElementText(element).split(" ");
        Lesson lesson = new Lesson();
        if(split.length == 1){
            lesson.setCourseName("");
            lesson.setTeacherName("");
            lesson.setWeekly("");
            lesson.setCourseRoom("");
        }else {
            lesson.setCourseName(split[0]);
            lesson.setTeacherName(split[2]);
            lesson.setWeekly(split[3]);
            lesson.setCourseRoom(split[4]);
        }
        return lesson;
    }


    /**
     * 解析字符串
     * @param context
     * @return
     */
    public static Element parse(String context) {
        return Jsoup.parse(context);
    }

    /**
     * 根据序号选择element
     * @param element
     * @param table
     * @param seq
     * @return
     */
    public static Element getElementBySeq(Element element,String table, Integer seq) {
        return element.select(table).get(seq);
    }

    /**
     * 通过标签获取element
     * @param context
     * @param label
     * @return
     */
    public static Element getElementByLabel(String context, String label) {
        return getElementByLabel(parse(context),label);
    }

    /**
     * 通过标签获取element
     * @param element
     * @param label
     * @return
     */
    public static Element getElementByLabel(Element element, String label) {
        return getElementByLabel(element, label, 0);
    }

    /**
     * 通过标签获取element
     * @param element
     * @param label
     * @param seq
     * @return
     */
    public static Element getElementByLabel(Element element, String label, Integer seq) {
        try {
            return element.select(label).get(seq);
        }catch (Exception e) {
            throw new ServiceException("未查询到该班级课表，请核对班级信息后查询");
        }
    }

    public static List<List<Lesson>> getMyCourseList(String context) {
        List<List<Lesson>> courseList = new ArrayList<>();
        Document document = Jsoup.parse(context);

        Element tbody = document.select("tbody").first();
        Elements trs = tbody.select("tr");
        for (int i = 1; i < 6; i ++) {
            List<Lesson> lessonRow = getCourseRow(trs.get(i));
            courseList.add(lessonRow);
        }
        return courseList;
    }

    /**
     * 获取一行共七天的课程
     * @param tr
     * @return
     */
    public static List<Lesson> getCourseRow(Element tr){
        List<Lesson> coursesRow = new ArrayList<>();
        Elements tds = tr.select("td");
        for (int i  = 0 ; i < 7; i ++){
            coursesRow.add(getLesson(tds.get(i)));
        }
        return coursesRow;
    }

    /**
     * 获取单节课程所有信息
     * @param td
     * @return
     */
    public static Lesson getLesson(Element td) {

        Lesson lesson = new Lesson();
        Element div = td.select("div").get(1);
        String[] split = div.text().split(" ");
        if(split.length == 1){
            lesson.setCourseName("");
            lesson.setTeacherName("");
            lesson.setWeekly("");
            lesson.setCourseRoom("");
        }else {
            lesson.setCourseName(split[0]);
            lesson.setTeacherName(split[1]);
            lesson.setWeekly(split[2]);
            lesson.setCourseRoom(split[3]);
        }
        return lesson;
    }

    /**
     * 通过id获取文本内容
     * @param element
     * @return
     */
    public static String getElementText(Element element) {
        String text = element.text();
        if (StringUtils.isNotEmpty(text)) {
            return text;
        }

        return "null";
    }



//
//    /**
//     * 一二节
//     */
//    private static final String ONE_TWO = "003D6B98F16A4D5AB56A88E531818B69-";
//
//    /**
//     * 三四节
//     */
//    private static final String THREE_FOUR = "71142B2A52FE4F66A743863A77108F21-";
//
//    /**
//     * 五六节
//     */
//    private static final String FIVE_SIX = "45E95DE465044957AAAC9645782B0F19-";
//
//    /**
//     * 七八节
//     */
//    private static final String SEVEN_EIGHT = "FC4B748390014E92AC4C3B0ACE70D9A4-";
//
//    /**
//     * 九十节
//     */
//    private static final String NINE_TEN = "E64AA0AE1AD542F992B291284B3FB263-";
//
//    /**
//     *  last id
//     */
//    private static final String END_ID = "-2";

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
