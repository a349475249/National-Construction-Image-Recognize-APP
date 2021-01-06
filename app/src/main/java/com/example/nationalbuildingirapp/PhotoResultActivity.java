package com.example.nationalbuildingirapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class PhotoResultActivity extends AppCompatActivity {
    String pred;
    Classifier classifier;
    TextView result_text,introduce_text;
    ImageView photo_imageView;
    Button return_Btn;

    private void initView() {
        result_text = findViewById(R.id.photo_result_text);
        return_Btn = findViewById(R.id.photo_result__return_btn);
        photo_imageView = findViewById(R.id.photoimageview);
        introduce_text=findViewById(R.id.photo_introduce_text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_result);
        initView();
        Intent intent = getIntent();
        //获取上个界面拍摄的图像URI
        Uri imageUri = getIntent().getData();
        //将拍摄的图像显示并识别出来
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().
                    openInputStream(imageUri));
            bitmap = Utils.rotateMyBitmap(bitmap);
            //载入识别模型
            classifier= new Classifier(Utils.assetFilePath(this, "resnet18_traced.pt"));
            pred = classifier.predict(bitmap);
            result_text.setText(pred);
            photo_imageView.setImageBitmap(bitmap);
            if (pred.equals("苗族")){
                introduce_text.setText("    苗族建筑有吊脚楼和落地式房屋。吊脚楼一般以四排三间为一幢，每排木柱一般9根，即五柱四瓜，一般分三层，上层储谷，中层住人，下层楼脚围栏成圈，作堆放杂物或关养牲畜。落地式房屋多分布在黔东湘西的一些平坦地区，中柱直接建立在平坦的地上，没有吊脚。以三层为主，第一层即落地层，左右两排为日常生活之用，进伸二或三间，中间排为中堂，后为神龛。一般家禽都在离正房屋几米的周围，而牛圈羊舍则更远些。");
            }
            else if (pred.equals("侗族")){
                introduce_text.setText("    侗族地区的民居具有鲜明的地方特点和浓郁的民族特色，建筑多为木质结构，贵州侗族分为“北侗”、“南侗”两个部分。北侗地区的民居与当地汉族的民居极为相似，一般都是一楼一底、四榀三间的木结构楼房。南部侗族地区盛产杉木，民居建筑体积较大，房屋高度很不一般。在竹木掩映的侗寨中,面阔五间.高三四层的庞然大物比比皆是。");
            }
            else if (pred.equals("黎族")){
                introduce_text.setText("    黎族主要有船形茅屋和金字形茅屋两种样式。船形屋是黎族最传统也是最具代表性的住宅。它以木条、竹子、红白藤和茅草为建筑材料，房屋的骨架用竹木构成，十分原始和简单，属于传统竹木结构建筑。金字形茅屋是黎族人民在与周边民族交往时吸收过来的住宅类型，20世纪50年代后普遍出现于黎族社会。它以树干作为支架，竹木编墙，用稻草与泥混合后抹成泥墙。");
            }
            else if (pred.equals("布依族")){
                introduce_text.setText("    布依族民居多为干栏式楼房或半边楼（前半部正面看是楼，后半部背面看是平方）式的石板房。贵州的镇宁、安顺等布依族地区盛产优质石料，当地布依族因地制宜，就地取材，用石料修造出一幢幢颇具民族特色的石板房。石板房以石条或石块砌墙，以石板盖顶，风雨不透。总之，除檩条、椽子是木料外，其余全是石料，甚至家用的桌、凳、灶、钵都是石头凿的。");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoResultActivity.this.finish();
            }
        });
    }
}
