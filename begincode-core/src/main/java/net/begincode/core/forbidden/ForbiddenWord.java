package net.begincode.core.forbidden;

/**
 * Created by saber on 2016/10/18.
 */
public class ForbiddenWord implements ForbiddenWordInitMapper{



    /**
     * 添加违禁字到txt，并更新违禁字容器
     * */
    public int addSensitiveWord(String[] strings){
        ForbiddenWordFilter forbiddenWordFilter = new ForbiddenWordFilter();
        ForbiddenWordInit forbiddenWordInit = new ForbiddenWordInit();
        try{
            forbiddenWordInit.writeSensitiveWordFile(strings);
        }catch (Exception e){
            e.printStackTrace();
        }
        forbiddenWordFilter.readSensitiveWord(false);
        return 1;
    }

}
