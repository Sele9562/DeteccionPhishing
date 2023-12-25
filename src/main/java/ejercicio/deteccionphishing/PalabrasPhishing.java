
package ejercicio.deteccionphishing;

import java.util.HashMap;
import java.util.Map;

public class PalabrasPhishing {
    
     
    public static final Map<String, Integer> KEYWORDS_MAP = createKeywordsMap();

    private static Map<String, Integer> createKeywordsMap() {
        Map<String, Integer> keywordsMap = new HashMap<>();
        
        
        keywordsMap.put("bloqueo", 2);  // Algo probable
        keywordsMap.put("compromiso", 3);  // Muy probable
        keywordsMap.put("datos", 2);  // Algo probable
        keywordsMap.put("cuenta bancaria", 1);  // Poco probable
        keywordsMap.put("protección", 2);  // Algo probable
        keywordsMap.put("verificar", 3);  // Muy probable
        keywordsMap.put("clave", 2);  // Algo probable
        keywordsMap.put("urgencia", 3);  // Muy probable
        keywordsMap.put("premio", 2);  // Algo probable
        keywordsMap.put("ganador", 3);  // Muy probable
        keywordsMap.put("oportunidad", 2);  // Algo probable
        keywordsMap.put("exclusivo", 3);  // Muy probable
        keywordsMap.put("tarjeta de crédito", 2);  // Algo probable
        keywordsMap.put("crédito", 2);  // Algo probable
        keywordsMap.put("ganador", 3);  // Muy probable
        keywordsMap.put("seleccionado", 3);  // Muy probable
        keywordsMap.put("actualización", 2);  // Algo probable
        keywordsMap.put("inicio de sesión", 3);  // Muy probable
        keywordsMap.put("caducado", 2);  // Algo probable
        keywordsMap.put("confirmación", 3);  // Muy probable
        keywordsMap.put("verificación de identidad", 2);  // Algo probable
        keywordsMap.put("cierre de cuenta", 3);  // Muy probable
        keywordsMap.put("felicidades", 2);  // Algo probable
        keywordsMap.put("información confidencial", 3);  // Muy probable
        keywordsMap.put("gratis", 2);  // Algo probable
        keywordsMap.put("limitado", 3);  // Muy probable
        keywordsMap.put("autorizado", 2);  // Algo probable
        keywordsMap.put("negocio", 1);  // Poco probable
        keywordsMap.put("confidencialidad", 3);  // Muy probable
        keywordsMap.put("cancelación", 2);  // Algo probable
        keywordsMap.put("llame ahora", 3);  // Muy probable

        return keywordsMap;
    }
}
