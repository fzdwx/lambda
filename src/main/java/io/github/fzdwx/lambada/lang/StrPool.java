package io.github.fzdwx.lambada.lang;

import java.io.File;

/**
 * str pool.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 12:05
 */
public interface StrPool {

    String EMPTY = "";

    String SLASH = "/";

    String CONCAT = "-";

    String DOUBLE_SLASH = "//";

    char SLASH_CHAR = '/';

    String WINDOWS_FOLDER_SEPARATOR = "\\";

    String TOP_PATH = "..";

    String CURRENT_PATH = ".";

    char EXTENSION_SEPARATOR = '.';

    String FILE_SEPARATOR = File.separator;

    char FILE_SEPARATOR_CHAR = File.separatorChar;

    String FILE_PATH_SEPARATOR = File.pathSeparator;

    char FILE_PATH_SEPARATOR_CHAR = File.pathSeparatorChar;


}