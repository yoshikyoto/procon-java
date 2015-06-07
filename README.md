プロコンで使えるアルゴリズム Java辺
====

# ブログ

http://yoshiki-utakata.hatenablog.com/


## グラフアルゴリズム

### ワーシャルフロイド法

```java
/**
* ワーシャルフロイド法
* コストが0だとエッジがないとみなされる版
*/
static void warshallFloyd(int[][] g, int n){
for (int k = 0; k < n; k++) {
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
if(i == j) continue;
if(g[i][k] == 0 || g[k][j] == 0) continue;
if(g[i][j] == 0) {
g[i][j] = g[i][k] + g[k][j];
} else {
g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
}
}
}
}
}
```
