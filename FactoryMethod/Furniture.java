package FactoryMethod;

public class Furniture {
    public static void main(String[] args) {
        FurnitureFactory sofa=new SofaFactory();
        FurnitureFactory table=new TableFactory();
        FurnitureFactory chair=new ChairFactory();

        FurnitureItems item1=sofa.createFurniture();
        item1.create();
        FurnitureItems item2=table.createFurniture();
        item2.create();
        FurnitureItems item3=chair.createFurniture();
        item3.create();

    }
}

//main funrniture interface
//below remaining classes are implemetation classes
interface FurnitureItems{
    void create();
}

class Chair implements FurnitureItems{
    @Override
    public void create() {
        System.out.println("This is Chair");
    }
}


class Table implements FurnitureItems{
    @Override
    public void create() {
        System.out.println("This is Table");
    }
}


class Sofa implements FurnitureItems{
    @Override
    public void create() {
        System.out.println("This is Sofa");
    }
}


//this is Factory Interface
//below remaining classes are Factory Interface Implemetation classes
interface FurnitureFactory{
    FurnitureItems createFurniture();
}

class ChairFactory implements FurnitureFactory{
    @Override
    public FurnitureItems createFurniture() {
        return new Chair();
    }
}

class SofaFactory implements FurnitureFactory{
    @Override
    public FurnitureItems createFurniture() {
        return new Sofa();
    }
}

class TableFactory implements FurnitureFactory{
    @Override
    public FurnitureItems createFurniture() {
        return new Table();
    }
}