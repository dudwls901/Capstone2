package kr.co.ilg.activity.findwork;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone2.R;

import java.util.ArrayList;

import kr.co.ilg.activity.login.Sharedpreference;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View dialogView;
    Button btnWorkInfo, btnSupply, btnPick;
    Intent intent;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,pay,job,place,office,current_people,total_people;
        LinearLayout linear1;
        MyViewHolder(View view){
            super(view);
            title=view.findViewById(R.id.title);
            date=view.findViewById(R.id.date);
            pay=view.findViewById(R.id.pay);
            job=view.findViewById(R.id.job);
            place=view.findViewById(R.id.place);
            office=view.findViewById(R.id.office);
            current_people=view.findViewById(R.id.current_people);
            total_people=view.findViewById(R.id.total_people);
            linear1=view.findViewById(R.id.linear1);

        }
    }

    private  Context context;
    private ArrayList<ListViewItem> workInfo;
    public ListAdapter(Context context, ArrayList<ListViewItem> workInfo){
        this.context=context;
        this.workInfo=workInfo;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_custom,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder) holder;
        if (workInfo.get(position).urgency.equals("0")) {
            myViewHolder.title.setText(workInfo.get(position).title);
            myViewHolder.date.setText(workInfo.get(position).date);
            myViewHolder.pay.setText(workInfo.get(position).pay);
            myViewHolder.job.setText(workInfo.get(position).job);
            myViewHolder.place.setText(workInfo.get(position).place);
            myViewHolder.office.setText(workInfo.get(position).office);
            myViewHolder.current_people.setText(workInfo.get(position).current_people);
            myViewHolder.total_people.setText(workInfo.get(position).total_people);
        }
        else if (workInfo.get(position).urgency.equals("1")) {
            myViewHolder.title.setText(workInfo.get(position).title);
            myViewHolder.date.setText(workInfo.get(position).date);
            myViewHolder.pay.setText(workInfo.get(position).pay);
            myViewHolder.job.setText(workInfo.get(position).job);
            myViewHolder.place.setText(workInfo.get(position).place);
            myViewHolder.office.setText(workInfo.get(position).office);
            myViewHolder.current_people.setText(workInfo.get(position).current_people);
            myViewHolder.total_people.setText(workInfo.get(position).total_people);
            myViewHolder.linear1.setBackgroundColor(context.getResources().getColor(R.color.UrgencyColor));
        }


        // business_reg_num 비교해서 내 구인글이면 다이얼로그 띄우고
        // 남의 글이면 workinfo액티비티
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                context = view.getContext();
                if(workInfo.get(position).business_reg_num.equals(Sharedpreference.get_business_reg_num(context,"business_reg_num","managerinfo")))
                {
                    final AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    dialogView = View.inflate(context, R.layout.myworkwritingdialog, null);
                    dlg.setView(dialogView);
                    btnWorkInfo = dialogView.findViewById(R.id.btnWorkInfo);
                    btnSupply = dialogView.findViewById(R.id.btnSupply);
                    btnPick = dialogView.findViewById(R.id.btnPick);
                    dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    btnWorkInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Context context = v.getContext();
                            Intent intent = new Intent(context, WorkInfoActivity.class);
                            intent.putExtra("jp_title", workInfo.get(position).title);
                            intent.putExtra("field_address", workInfo.get(position).place);
                            intent.putExtra("manager_office_name", workInfo.get(position).office);
                            intent.putExtra("job_name", workInfo.get(position).job);
                            intent.putExtra("jp_job_cost", workInfo.get(position).pay);
                            intent.putExtra("jp_job_date", workInfo.get(position).date);
                            intent.putExtra("jp_job_start_time", workInfo.get(position).jp_job_start_time);
                            intent.putExtra("jp_job_finish_time", workInfo.get(position).jp_job_finish_time);
                            intent.putExtra("jp_job_tot_people", workInfo.get(position).total_people);
                            intent.putExtra("jp_contents", workInfo.get(position).jp_contents);
                            intent.putExtra("business_reg_num", workInfo.get(position).business_reg_num);
                            intent.putExtra("jp_num",workInfo.get(position).jp_num);
                            intent.putExtra("field_name",workInfo.get(position).field_name);
                            intent.putExtra("jp_is_urgency",workInfo.get(position).urgency);
                            intent.putExtra("field_code", workInfo.get(position).field_code);

                            context.startActivity(intent);
                        }
                    });
                    btnSupply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context = v.getContext();
                            intent = new Intent(context, ApplyStateActivity.class);
                            //내 아이디(스피너에 날짜안 지난것들로 add), 구인글 번호(apply테이블 select 재료), 구인글 제목(스피너에 디폴트값)
                            intent.putExtra("business_reg_num", workInfo.get(position).business_reg_num);
                            intent.putExtra("jp_num",workInfo.get(position).jp_num);
                            intent.putExtra("jp_title",workInfo.get(position).title);
                            context.startActivity(intent);
                        }
                    });
                    btnPick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context = v.getContext();
                            intent = new Intent(context, PickStateActivity.class);
                            //내 아이디(스피너에 날짜안 지난것들로 add), 구인글 번호(apply테이블 select 재료), 구인글 제목(스피너에 디폴트값)
                            intent.putExtra("business_reg_num", workInfo.get(position).business_reg_num);
                            intent.putExtra("jp_num",workInfo.get(position).jp_num);
                            intent.putExtra("jp_title",workInfo.get(position).title);
                            context.startActivity(intent);
                        }
                    }); dlg.show();
                }
                //내 구인글이 아닐 때
               else {

                    Context context = view.getContext();
                    Intent intent = new Intent(context, WorkInfoActivity.class);
                    intent.putExtra("jp_title", workInfo.get(position).title);
                    intent.putExtra("field_address", workInfo.get(position).place);
                    intent.putExtra("manager_office_name", workInfo.get(position).office);
                    intent.putExtra("job_name", workInfo.get(position).job);
                    intent.putExtra("jp_job_cost", workInfo.get(position).pay);
                    intent.putExtra("jp_job_date", workInfo.get(position).date);
                    intent.putExtra("jp_job_start_time", workInfo.get(position).jp_job_start_time);
                    intent.putExtra("jp_job_finish_time", workInfo.get(position).jp_job_finish_time);
                    intent.putExtra("jp_job_tot_people", workInfo.get(position).total_people);
                    intent.putExtra("jp_contents", workInfo.get(position).jp_contents);
                    intent.putExtra("business_reg_num", workInfo.get(position).business_reg_num);
                    intent.putExtra("field_name",workInfo.get(position).field_name);
                    intent.putExtra("field_code",workInfo.get(position).field_code);
                    context.startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return  workInfo.size();
    }
}
