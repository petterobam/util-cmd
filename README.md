# util-cmd

命令行常用小功能命令行工具
- [X] 日期时间相关工具
- [X] URL相关工具
- [X] Base64相关工具
- [ ] Corn表达式相关工具 
- [X] 自动安装脚本

# 快速使用

请先找个安装目录，打开系统的命令行工具，执行下面对应操作系统的安装命令。
```shell
# install in mac/linux
git clone https://github.com/petterobam/util-cmd.git
cd util-cmd/bin
sh install.sh

# install in windows cmd
git clone https://github.com/petterobam/util-cmd.git
cd .\util-cmd\bin
.\install.cmd

# install in windows powershell
git clone https://github.com/petterobam/util-cmd.git
cd .\util-cmd\bin
.\install.ps1
```

# 使用方法
```shell
$ util -h
 命令行命令行工具 V0.0.1
------------------------------------------------------------------------------
 util [工具类型] [动作类型] [参数...（多个空格分隔，含空格的用 " 或 ' 括起来）]
  [time] 日期时间工具！
   1. 工具类型参数: -time(t)【日期】！
   2. 动作类型参数：-format(f)【格式化】| -stamp(s)【时间戳】！
  [url] URL工具！
   1. 工具类型参数: -url【u】！
   2. 动作类型参数：-decode(d)【解密】| -encode(e)【加密】！
  [base64] Base64工具！
   1. 工具类型参数: -base64【b64】！
   2. 动作类型参数：-decode(d)【解密】| -encode(e)【加密】！
```

## 时间相关

```shell
$ util -t -f now
 工具类型：时间【-t】
 动作：获得【指定日期】格式化样式
 日期指定：now
 格式：yyyy-MM-dd HH:mm:ss
 格式化的日期：2020-09-25 13:47:21

$ util -t -f -1d2h3m
 工具类型：时间【-t】
 动作：获得【指定日期】格式化样式
 日期指定：-1d2h3m
 格式：yyyy-MM-dd HH:mm:ss
 格式化的日期：2020-09-24 11:44:34

$ util -t -f 1601012841000
 工具类型：时间【-t】
 动作：获得【指定日期】格式化样式
 日期指定：1601012841000
 格式：yyyy-MM-dd HH:mm:ss
 格式化的日期：2020-09-25 13:47:21

$ util -t -s "2020-09-25 13:47:21"
 工具类型：时间【-t】
 动作：获得【指定日期】的时间戳
 日期指定：2020-09-25 13:47:21
 识别为：2020-09-25 13:47:21
 时间戳：1601012841000
```