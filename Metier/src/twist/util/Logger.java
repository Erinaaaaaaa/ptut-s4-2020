package twist.util;

import static org.fusesource.jansi.Ansi.*;
import org.fusesource.jansi.AnsiConsole;

/*
 * Classe utilitaire Logger.java
 * Utilise la librarie jansi-1.18.jar pour formater et colorier les messages
 * d'information dans la console Windows
 */
public class Logger
{
    // 0 - Très verbeux ; 4 - Erreurs fatales uniquement
    public static final int LOG_LEVEL = 0;

    static { AnsiConsole.systemInstall(); }

    public static void verbose(String message)
    {
        if (LOG_LEVEL <= 0)
            System.out.println(ansi().render("@|bold,black [VRB] "+message+"|@"));
    }

    public static void information(String message)
    {
        if (LOG_LEVEL <= 1)
            System.out.println(ansi().render("@|white [INF] "+message+"|@"));
    }

    public static void warning(String message)
    {
        if (LOG_LEVEL <= 2)
            System.out.println(ansi().render("@|yellow [WRN] "+message+"|@"));
    }

    public static void error(String message)
    {
        if (LOG_LEVEL <= 3)
            System.out.println(ansi().render("@|red [ERR] "+message+"|@"));
    }

    public static void fatal(String message)
    {
        if (LOG_LEVEL <= 4)
            System.out.println(ansi().render("@|bold,red [FTL] "+message+"|@"));
    }
}
