# KE homework 2
# 一、分词
1. 在[斯坦福分词](https://nlp.stanford.edu/software/segmenter.shtml)这个链接中下载斯坦福分词程序stanford-segmenter-2017-06-09，版本不是很重要。下载后解压。
2. 建立分词的java project，把github里的SegDemo.java放到项目的src文件夹里，把斯坦福分词程序里的arabic文件夹和data文件夹复制到项目目录下，java工程引用这三个jar文件：stanford-segmenter-3.7.0.jar、stanford-segmenter-3.7.0-javadoc.jar、stanford-segmenter-3.7.0-sources.jar，可以把jar都复制到项目目录下，也可以直接引用。将待分词的文档textdata.rar解压放到项目目录下。
3. 为SegDemo.java的运行添加参数“文件名”，在工程目录上，右键Run As -> RunConfigurations，在program arguments下面写上“文件名”即可。例如要分词的文件名是“1.txt”，那写上“1.txt”就可以，如果有多个文件，一行一个文件名都放进去就可以。
4. 运行程序即可得到分词结果，将其复制到segment.txt里准备进行命名实体识别。
以上内容参考[stanford-segmenter中文分词基本使用](http://blog.csdn.net/yangyangrenren/article/details/54730783)。

# 二、训练
1. 在[斯坦福NER](https://nlp.stanford.edu/software/CRF-NER.shtml)下载斯坦福NER程序stanford-ner-2017-06-09.zip，在[斯坦福中文NER](https://stanfordnlp.github.io/CoreNLP/index.html#download)下载stanford-chinese-corenlp-2017-06-09-models.jar，都解压好。
2. stanford-chinese-corenlp-2017-06-09-models解压后的文件夹的edu\stanford\nlp\models\ner目录下找到chinese.misc.distsim.crf.ser.gz和chinese.misc.distsim.prop，将这两个文件复制到stanford-ner-2017-06-09文件夹中的classifiers文件夹里面，和几个english的放在一起。
3. 把github里的austen.prop和testdata.tsv放到stanford-ner-2017-06-09文件夹里，在该目录下打开命令行，输入java -cp stanford-ner.jar edu.stanford.nlp.ie.crf.CRFClassifier -prop austen.prop，如果没有报错的话，会得到ner-model.ser.gz，这就是训练好的模型，可以用来进行命名实体识别。
4. austen.prop是配置文件，里面有相关注释，可以按照需要更改。
5. testdata.tsv是训练数据，可以按照格式输入标记好的数据。

# 三、识别
1. 建立命名体识别的java project，把github里的NERDemo.java复制到项目的src文件夹里，把分好词的文件segment.txt和训练好的模型ner-model.ser.gz放到目录下，把之前训练模型的classifiers文件夹整个复制到项目目录下，里面包括chinese的一共有十个文件。
2. java工程引用这四个jar文件：stanford-ner.jar、stanford-ner-3.8.0.jar、stanford-ner-3.8.0-javadoc.jar、stanford-ner-3.8.0-sources.jar，这四个文件在训练模型的那个stanford-ner-2017-06-09文件夹里有，可以直接引用也可以复制到本项目工程下再引用。
3. 运行程序，即可得到识别结果。
4. 训练和识别内容参考：[自然语言分析之命名实体识别_Stanford Named Entity Recognizer (NER)简单实例](http://blog.csdn.net/limisky/article/details/17025861)和[使用Stanford NLP工具实现中文命名实体识别](http://blog.csdn.net/sparkexpert/article/details/49497231)。
