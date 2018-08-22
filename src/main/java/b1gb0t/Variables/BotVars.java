package b1gb0t.Variables;

import java.awt.*;

public class BotVars {
        private static String m_Token = "";
        private static String m_Prefix = "";
        private static String m_Image = "";
        private static String m_OwnerId = "";
        private static String m_Game = "";
        private static Color m_Color = Color.GREEN;
        public static String token()
        {
            return m_Token;
        }
        public static String prefix(){
            return m_Prefix;
        }
        public static String image(){
            return m_Image;
        }
        public static String ownerId(){ return m_OwnerId; }
        public static String game(){ return m_Game; }
        public static Color color(){
            return m_Color;
        }

        public static void setToken(String token){
            m_Token = token;
        }
        public static void setPrefix(String prefix){
            m_Prefix = prefix;
        }
        public static void setImage(String image){
            m_Image = image;
        }
        public static void setOwner(String id){
            m_OwnerId = id;
        }
        public static void setColor(Color color){
            m_Color = color;
        }
        public static void setGame(String game){
            m_Game = game;
        }
}
