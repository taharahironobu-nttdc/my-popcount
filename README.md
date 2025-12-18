# 問題
[AtCoderABC380](https://atcoder.jp/contests/abc380/tasks/abc380_d)

# 実行環境がない場合
AtCoder内で実行できますが、AtCoderに登録しない場合は下記オンライン実行環境で試してみてください。\
[オンラインJavaコンパイラ](https://www.mycompiler.io/ja/online-java-compiler)

# ソースコード
[Main.java](https://github.com/taharahironobu-nttdc/my-popcount/blob/main/Main.java) <- ビット演算を利用した正しいコード \
[OldMain.java](https://github.com/taharahironobu-nttdc/my-popcount/blob/main/OldMain.java) <- 普通の文字列連結を繰り返して大文字小文字反転を行うコード

# 入力例

```
aB
16
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
```

# 出力例
```
a B A b A b a B A b a B a B A b
```

# 補足
操作前の S=`aB`です。 \
aB に 1 回操作を行うと `aBAb` となります。\
aB に 2 回操作を行うと `aBAbAbaB` となります。\
n 回の操作を終えた後の S= `aBAbAbaBAbaBaBAb...` です。
