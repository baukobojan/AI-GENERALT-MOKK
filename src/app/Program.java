package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Program {
    
    private static List<Fuvar> fuvarok;
    private static List<String> sorok;
    
    public static void main(String[] args) throws IOException {
       program();
    }
    
    private static void program() throws IOException{
      System.out.println("1. Összes fuvar értéke: " + OsszesFuvarErteke() + " euro");
      System.out.println("2. Legdrágább fuvar rendszáma: " + legdragabbFuvarRendszama());
      System.out.println("3. Legolcsóbb fuvar értéke: " + legolcsobbFuvarErteke() + " euro");
      System.out.println("4. Hány kártyás fizetés volt: " + KartyasFietesekSzama());
      System.out.println("5. Minden fizetési mód szerepel: " + MindenFizetesiModFelhasznalva());
      System.out.println("6. Hány darab autó van a rendszerbe: " + OsszesAutoDarabSzama());
      System.out.println("7. Hányféle fizetési mód van: " + FizetesiModokSzama());
      
    };
    
    static void ini() throws IOException{
        sorok = Files.readAllLines(Path.of("fuvarok.csv"));
        fuvarok = new ArrayList<>();
        for (String sor : sorok) {
            fuvarok.add(new Fuvar(sor));
        }
    };
    
    public static int OsszesFuvarErteke() throws IOException {
        ini();
        int osszeg = 0;
        for (Fuvar f : fuvarok) {
            osszeg += f.getOsszeg();
        }
        return osszeg;
    };
    
    public static String legdragabbFuvarRendszama() {
        Fuvar legdragabb = fuvarok.get(0);
        for (Fuvar f : fuvarok) {
            if (f.getOsszeg()> legdragabb.getOsszeg()) {
                legdragabb = f;
            }
        }
        return legdragabb.getRsz();
    };
    
    public static int legolcsobbFuvarErteke() {
        if (fuvarok.isEmpty()) return 0;
        int min = (int) fuvarok.get(0).getOsszeg();
        for (Fuvar f : fuvarok) {
            if (f.getOsszeg()< min) {
                min = (int) f.getOsszeg();
            }
        }
        return min;
    };
    
    public static int KartyasFietesekSzama(){
        int db = 0;
        for (Fuvar fuvar : fuvarok) {
            if(fuvar.getFizmod().equals("kártya")){
                db++;
            }
        }
        return db;
  
    };
    
    public static boolean MindenFizetesiModFelhasznalva(){
            Set<String> talaltModok = new HashSet<>();
            for (Fuvar fuvar : fuvarok) {
               talaltModok.add(fuvar.getFizmod());
            }
            return talaltModok.size() == 4;
        };
    
    public static int OsszesAutoDarabSzama(){
        Set<String> rendszamok = new HashSet<>();
            for (Fuvar fuvar : fuvarok) {
               rendszamok.add(fuvar.getRsz());
            }
            return rendszamok.size();
    };
    public static int FizetesiModokSzama(){
      Set<String> fizetesiModok = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            fizetesiModok.add(fuvar.getFizmod());
        }
        return fizetesiModok.size();
    };
}

/* 1 összes fuvar erteke
2. legdragabb fuvar rendzsama
3. legolcsobb fuvar forintba
4. hany kartyas fizetes volt
5. minden fizetési mód meghatarozott
6. hany darab auto van a rendszerben
7. hanyfele fizetesi mod van
8. melyik auto mennyi fuvart teljesitett
*/