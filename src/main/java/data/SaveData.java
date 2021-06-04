package data;

import data.components.Engine;
import data.components.Ksa;
import data.components.Planer;
import data.components.limitedResource.*;
import io.reader.FilesReader;

import java.util.List;

public class SaveData {
    public static List<Engineer> engineersList = FilesReader.readInfo(Engineer.class);
    public static List<Aircraft> aircraftList = FilesReader.readInfo(Aircraft.class);
    public static List<Ksa> ksaList = FilesReader.readInfo(Ksa.class);
    public static List<Engine> enginesList = FilesReader.readInfo(Engine.class);
    public static List<Planer> planersList = FilesReader.readInfo(Planer.class);
    public static List<MainBreak> mainBreaksList = FilesReader.readInfo(MainBreak.class);
    public static List<FrontBreak> frontBreaksList = FilesReader.readInfo(FrontBreak.class);
    public static List<MainWheel> mainWheelsList = FilesReader.readInfo(MainWheel.class);
    public static List<FrontWheel> frontWheelsList = FilesReader.readInfo(FrontWheel.class);
    public static List<CylinderOfRetractionExtension> cylindersList = FilesReader.readInfo(CylinderOfRetractionExtension.class);

}

