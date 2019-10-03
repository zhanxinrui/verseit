package com.screwmachine55open.verseit.util;

import lombok.*;

import java.lang.reflect.Field;
/**
 * @author ：xrzhan
 * @date ：Created in 2019/5/5 15:06
 * @description：${description}
 * @modified By：
 * @version: $version$
 */


public class CombineBeans {

    /**
     * @Title: combineSydwCore
     * @Description: 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，
     *               那么sourceBean中的值会覆盖tagetBean重点的值
     * @author: WangLongFei
     * @date: 2017年12月26日 下午1:53:19
     * @param sourceBean
     *            被提取的对象bean
     * @param targetBean
     *            用于合并的对象bean
     * @return targetBean 合并后的对象
     * @return: Object
     */
    @SuppressWarnings("unused")
    public Object combineSydwCore(Object sourceBean, Object targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = sourceBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null)) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

    // 测试 combineBeans方法
    public static void main(String[] args) {
        Student sourceModel = new Student(); // 第一个对象
        Student targetModel = new Student(); // 第二个model对象

        sourceModel.setSex("1");
        sourceModel.setcName("张三");

        targetModel.setSex("2");
        targetModel.setcName("李四");
        targetModel.setCardName("身份证");
        targetModel.setCardNumber("222222222222222222222");

        CombineBeans test = new CombineBeans();
        Object res = test.combineSydwCore(sourceModel, targetModel);
        System.out.println(res);


    }
}

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
 class Student {
    private String sex;
    private String cName;
    private String cardName;
    private String cardNumber;

    public String getSex() {
         return sex;
     }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
