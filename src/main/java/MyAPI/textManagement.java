package MyAPI;

public class textManagement {

    /**
     * Comprueba si una cadena de texto es válida, es decir, si contiene solo
     * caracteres permitidos. Los caracteres permitidos incluyen letras
     * mayúsculas, letras minúsculas, dígitos del 0 al 9 y espacios en blanco.
     *
     * @param text La cadena de texto que se va a verificar.
     * @return true si la cadena es válida; de lo contrario, false.
     */
    public static boolean isValid(String text) {
        Boolean valid = true;
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (allowedCharacters.indexOf(c) == -1) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Comprueba si una cadena de texto es válida, es decir, si contiene solo
     * caracteres permitidos, excluyendo dígitos. Los caracteres permitidos
     * incluyen letras mayúsculas, letras minúsculas y espacios en blanco.
     *
     * @param text La cadena de texto que se va a verificar.
     * @return true si la cadena es válida; de lo contrario, false.
     */
    public static boolean isValidWithMinus(String text) {
        Boolean valid = true;
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (allowedCharacters.indexOf(c) == -1) {
                valid = false;
            }
        }
        return valid;
    }
}
