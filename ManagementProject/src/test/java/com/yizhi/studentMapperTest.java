package com.yizhi;


import com.yizhi.student.dao.StudentInfoDao;
import com.yizhi.student.domain.StudentInfoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class studentMapperTest {

    @Autowired(required = false)
    StudentInfoDao  studentInfoDao;


    @Test
    public void selectStudent(){
        System.out.println(studentInfoDao.get(5));
    }


    @Test
    public void selectList(){
        Map<String,Object> test = new HashMap<>();
        test.put("tocollege",2);
//        test.put("studentName","bo");
//        test.put("tomajor",2);
        List<StudentInfoDO> studentInfoDOList = studentInfoDao.list(test);
        for (StudentInfoDO s: studentInfoDOList){
            System.out.println(s);
        }
    }

    @Test
    public void deleteOne(){
        System.out.println(studentInfoDao.remove(5));
    }

    @Test
    public void deleteOneMore(){
        Integer[] remove = new Integer[]{6,7,8};
        System.out.println(studentInfoDao.batchRemove(remove));
    }


    @Test
    public void addTest(){
        StudentInfoDO studentInfoDO = new StudentInfoDO();

        studentInfoDO.setStudentId("3120200971374");
        studentInfoDO.setExamId("110011");
        studentInfoDO.setClassId(2);
        studentInfoDO.setStudentName("鸿莲");
        studentInfoDO.setCertify("5107032220125120");
        studentInfoDO.setMailAddress("四川省");
        studentInfoDO.setForeignLanaguage("英语");
        studentInfoDO.setStudentSex("男");
        studentInfoDO.setNation("高山族");
        studentInfoDO.setPolitical("少先队员");
        studentInfoDO.setCardId("1200139");
        studentInfoDO.setTelephone("19982712654");
        studentInfoDO.setSubjectType(1);
        studentInfoDO.setTocollege(1);
        studentInfoDO.setTocampus(1);
        studentInfoDO.setTomajor(1);
        studentInfoDO.setBirthplace("广安市邻水县");
        studentInfoDO.setGrade("1");
        studentInfoDO.setIsstate(0);
        studentInfoDO.setBirthday(new Date());
        studentInfoDO.setNote("好学生");
        studentInfoDO.setAddTime(new Date());
        studentInfoDO.setAddUserid(6);
        studentInfoDO.setEditTime(new Date());
        studentInfoDO.setEditUserid(6);
        studentInfoDao.save(studentInfoDO);
    }


    @Test
    public void update(){
        StudentInfoDO studentInfoDO = new StudentInfoDO();

        studentInfoDO.setStudentId("3120200971374");
        studentInfoDO.setExamId("110011");
        studentInfoDO.setClassId(2);
        studentInfoDO.setStudentName("鸿莲");
        studentInfoDO.setCertify("5107032220125120");
        studentInfoDO.setMailAddress("四川省");
        studentInfoDO.setForeignLanaguage("英语");
        studentInfoDO.setStudentSex("男");
        studentInfoDO.setNation("高山族");
        studentInfoDO.setPolitical("少先队员");
        studentInfoDO.setCardId("1200139");
        studentInfoDO.setTelephone("19982712654");
        studentInfoDO.setSubjectType(1);
        studentInfoDO.setTocollege(1);
        studentInfoDO.setTocampus(1);
        studentInfoDO.setTomajor(1);
        studentInfoDO.setBirthplace("广安市邻水县");
        studentInfoDO.setGrade("1");
        studentInfoDO.setIsstate(0);
        studentInfoDO.setBirthday(new Date());
        studentInfoDO.setNote("好学生");
        studentInfoDO.setAddTime(new Date());
        studentInfoDO.setAddUserid(6);
        studentInfoDO.setEditTime(new Date());
        studentInfoDO.setEditUserid(6);
        System.out.println(studentInfoDao.update(studentInfoDO));
    }

    @Test
    public void countTest(){
        Map<String,Object> test = new HashMap<>();
        test.put("classId",2);
        test.put("tocollege",1);
        test.put("tomajor",1);
        System.out.println(studentInfoDao.count(test));
    }



}
