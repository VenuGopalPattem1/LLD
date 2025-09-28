package Composite;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    public static void main(String[] args) {
        File a=new File("venu.jpeg", 20);
        File b=new File("vamsi.jpeg", 34);
        File c=new File("beauty.jpeg", 18);
        File d=new File("nar.jpeg", 11);
        File e=new File("vanga.jpeg", 22);

        Directory dir=new Directory("Gallery");
        dir.addFileToDir(a);
        dir.addFileToDir(b);
        dir.addFileToDir(c);
        dir.addFileToDir(d);

        Directory director=new Directory("Director");
        director.addFileToDir(e);
        dir.addFileToDir(director);

        dir.getFile("");
        System.out.println(dir.size());

    }
}

interface FileSystemComponent{
    void getFile(String indent);
    int size();
}

class File implements FileSystemComponent{
    private String name;
    private int size;

    public File(String name,int size){
        this.name=name;
        this.size=size;
    }
    @Override
    public void getFile(String indent) {
        System.out.println(indent + "- " + name + " (" + size + "MB)");
    }

    @Override
    public int size() {
        return size;
    }

}

class Directory implements FileSystemComponent{
    private String name;
    private List<FileSystemComponent> dir;

    public Directory(String name){
        this.name=name;
        dir=new ArrayList<>();
    }

    public void addFileToDir(FileSystemComponent li){
        dir.add(li);
    }

    @Override
    public void getFile(String indent) {
        System.out.println(indent + "+ " + name + "/");
        for(FileSystemComponent fs:dir){
            fs.getFile(indent + "  ");
        }
    }

    @Override
    public int size() {
        int ans=0;
        for(FileSystemComponent fs:dir){
            ans+=fs.size();
        }
        return ans;
    }
    
}
