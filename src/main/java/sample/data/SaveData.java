package sample.data;

import sample.data.components.Engine;
import sample.data.components.Ksa;
import sample.data.components.Planer;
import sample.data.components.limitedResource.*;
import sample.reader.ReadFile;

import java.util.List;

public  class SaveData {
    public static List<Engineer> engineersList = ReadFile.readInfo(Engineer.class);
    public static List<Aircraft> aircraftList = ReadFile.readInfo(Aircraft.class);
    public static List<Ksa> ksaList = ReadFile.readInfo(Ksa.class);;
    public static List<Engine> enginesList = ReadFile.readInfo(Engine.class);
    public static List<Planer> planersList = ReadFile.readInfo(Planer.class);
    public static List<MainBreak> mainBreaksList = ReadFile.readInfo(MainBreak.class);
    public static List<FrontBreak> frontBreaksList = ReadFile.readInfo(FrontBreak.class);
    public static List<MainWheel> mainWheelsList = ReadFile.readInfo(MainWheel.class);
    public static List<FrontWheel> frontWheelsList = ReadFile.readInfo(FrontWheel.class);
    public static List<CylinderOfRetractionExtension> cylindersList = ReadFile.readInfo(CylinderOfRetractionExtension.class);
}
