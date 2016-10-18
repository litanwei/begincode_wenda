package net.begincode.core.forbidden;

/**
 * Created by saber on 2016/10/18.
 */

public interface ForbiddenWordInitMapper {

    /**
     * 动态新增违禁字
     * */
    int addForbiddenWord(String[] strings);
}
