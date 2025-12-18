import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 高速入力用のスキャナーを初期化
        FastScanner fs = new FastScanner(System.in);

        // 基本文字列を文字配列として読み込む
        // 例: "aB" → ['a', 'B']
        char[] s = fs.next().toCharArray();
        
        // 文字列の長さを保存（ブロックサイズとして使用）
        int n = s.length;

        // クエリ数を読み込む
        int q = fs.nextInt();
        
        // 結果を格納するための StringBuilder（文字列連結を高速化）
        StringBuilder sb = new StringBuilder();

        // 各クエリを処理
        for (int i = 0; i < q; i++) {
            // k番目の文字を取得（1-indexed → 0-indexedに変換）
            // 例: 入力が1なら k=0, 入力が2なら k=1
            long k = fs.nextLong() - 1; 
            
            // ===== ブロック番号の計算 =====
            // k番目の文字がどのブロックに属するかを計算
            // 例: n=2の場合
            //   k=0,1 → block=0 (最初のブロック)
            //   k=2,3 → block=1 (2番目のブロック)
            //   k=4,5 → block=2 (3番目のブロック)
            long block = k / n; // k番目の文字が何ブロック目か
            
            // ===== 反転回数の計算 =====
            // ブロック番号を2進数表現したときの「1」のビット数を数える
            // これが奇数なら反転、偶数ならそのまま
            // 例:
            //   block=0 (2進数: 0)    → bitCount=0 → 偶数 → 反転なし
            //   block=1 (2進数: 1)    → bitCount=1 → 奇数 → 反転
            //   block=2 (2進数: 10)   → bitCount=1 → 奇数 → 反転
            //   block=3 (2進数: 11)   → bitCount=2 → 偶数 → 反転なし
            //   block=4 (2進数: 100)  → bitCount=1 → 奇数 → 反転
            //   block=5 (2進数: 101)  → bitCount=2 → 偶数 → 反転なし
            //   block=7 (2進数: 111)  → bitCount=3 → 奇数 → 反転
            int pc = Long.bitCount(block); // ブロック番号のビット数
            
            // ===== ブロック内の位置を計算 =====
            // k番目の文字がブロック内のどの位置にあるかを計算
            // 例: n=2の場合
            //   k=0 → idx=0 (s[0])
            //   k=1 → idx=1 (s[1])
            //   k=2 → idx=0 (s[0])
            //   k=3 → idx=1 (s[1])
            int idx = (int)(k % n); // ブロック内での位置
            
            // ===== 元の文字を取得 =====
            // ブロック内の位置に対応する元の文字を取得
            char c = s[idx];

            // ===== 反転判定と実行 =====
            // ビット数が奇数かどうかをチェック（最下位ビットが1かどうか）
            // (pc & 1) == 1 は pc % 2 == 1 と同じ意味（高速）
            if ((pc & 1) == 1) {
                // ビット数が奇数の場合、大文字小文字を反転
                if (Character.isUpperCase(c)) {
                    // 大文字 → 小文字
                    // 例: 'B' → 'b'
                    c = Character.toLowerCase(c);
                } else {
                    // 小文字 → 大文字
                    // 例: 'a' → 'A'
                    c = Character.toUpperCase(c);
                }
            }
            // ビット数が偶数の場合は何もしない（cはそのまま）

            // ===== 結果を追加 =====
            // 変換後の文字と空白を結果に追加
            sb.append(c).append(' ');
        }

        // 最終結果を出力
        System.out.println(sb.toString());
    }


    // ===== 高速入力用クラス =====
    // BufferedReaderより高速に大量のデータを読み込むための自作クラス
    static class FastScanner {
        // 入力ストリーム
        private final InputStream in;
        
        // バッファ（一度に65536バイト読み込む）
        private final byte[] buffer = new byte[1 << 16];
        
        // ptr: 現在読み込み位置, len: バッファ内の有効データ長
        private int ptr = 0, len = 0;

        // コンストラクタ
        FastScanner(InputStream is) { in = is; }

        // 1バイト読み込むメソッド
        private int read() throws IOException {
            // バッファを使い切った場合、新たにデータを読み込む
            if (ptr >= len) {
                len = in.read(buffer);  // バッファに新しいデータを読み込む
                ptr = 0;                // ポインタをリセット
                if (len <= 0) return -1; // データがない場合は-1を返す
            }
            // 現在位置のバイトを返し、ポインタを進める
            return buffer[ptr++];
        }

        // 次の文字列（空白区切り）を読み込むメソッド
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            
            // 空白文字（スペース、改行など）をスキップ
            while ((c = read()) != -1 && c <= ' ') ;
            
            // 空白でない文字を読み続ける
            while (c > ' ') {
                sb.append((char)c);  // 文字を追加
                c = read();          // 次の文字を読む
            }
            
            return sb.toString();
        }

        // 整数を読み込むメソッド
        int nextInt() throws Exception { 
            return Integer.parseInt(next()); 
        }
        
        // long型整数を読み込むメソッド
        long nextLong() throws Exception { 
            return Long.parseLong(next()); 
        }
    }
}
