import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 初期文字列を読み込み
        String s = sc.next();
        
        // 操作回数を読み込み
        int n = sc.nextInt();
        
        // 出力する位置のリストを読み込み
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = sc.nextInt();
        }
        
        // 操作を繰り返して文字列を構築
        StringBuilder result = new StringBuilder(s);
        
        // 必要な最大位置を求める
        int maxPos = 0;
        for (int pos : positions) {
            maxPos = Math.max(maxPos, pos);
        }
        
        // maxPosまで文字列を拡張
        while (result.length() < maxPos) {
            // 現在の文字列の大文字小文字を反転
            String flipped = flipCase(result.toString());
            result.append(flipped);
        }
        
        // 指定された位置の文字を出力
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result.charAt(positions[i] - 1));
        }
        System.out.println();
        
        sc.close();
    }
    
    // 文字列の大文字小文字を反転させる関数
    private static String flipCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }
}
