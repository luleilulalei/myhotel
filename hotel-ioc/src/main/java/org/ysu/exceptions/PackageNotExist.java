package org.ysu.exceptions;

public class PackageNotExist extends BaseException  {
    public PackageNotExist(String pkg) {
        super(pkg + " does not exist!!!");
    }
}
