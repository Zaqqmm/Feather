package top.zaqqmm.oss.feather.core.bean.reader;

import java.lang.annotation.Annotation;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-18 17:08
 */
public class Scanner {
    private String packageName;
    private boolean recursive;
    private Class<?> parent;
    private Class<? extends Annotation> annotation;

    private Scanner(Builder builder) {
        this.packageName=builder.packageName;
        this.recursive=builder.recursive;
        this.parent=builder.parent;
        this.annotation=builder.annotation;
    }
    public static class Builder{
        private String packageName;
        private boolean recursive;
        private Class<?> parent;
        private Class<? extends Annotation> annotation;

        public Builder packageName(String packageName){
            this.packageName = packageName;
            return this;
        }

        public Builder recursive(boolean recursive){
            this.recursive = recursive;
            return this;
        }
        public Builder parent(Class<?> parent){
            this.parent = parent;
            return this;
        }
        public Builder annotation(Class<? extends Annotation> annotation){
            this.annotation = annotation;
            return this;
        }

        public Scanner build(){
            return new Scanner(this);
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public Class<?> getParent() {
        return parent;
    }

    public void setParent(Class<?> parent) {
        this.parent = parent;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "packageName='" + packageName + '\'' +
                ", recursive=" + recursive +
                ", parent=" + parent +
                ", annotation=" + annotation +
                '}';
    }
}
