package schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import schooljournal.belenkov.samsung.ru.schooljournalexample3.R;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.controller.activities.TeacherDetailsActivity;
import schooljournal.belenkov.samsung.ru.schooljournalexample3.model.entities.peoples.Teacher;

public class TeachersRecyclerAdapter extends RecyclerView.Adapter<TeachersRecyclerAdapter.TeachersHolder> {

    private Teacher[] teachersList;
    private Context context;

    public TeachersRecyclerAdapter(Teacher[] teachersList, Context context) {
        this.teachersList = teachersList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeachersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.teacher_item, viewGroup, false);
        return new TeachersHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersHolder teachersHolder, int i) {
        teachersHolder.bind(teachersList[i]);
    }

    @Override
    public int getItemCount() {
        return teachersList.length;
    }

    static class TeachersHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phone;
        private TextView qualification;

        public TeachersHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.teacherName);
            phone = itemView.findViewById(R.id.teacherNumber);
            qualification = itemView.findViewById(R.id.teacherQualification);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TeacherDetailsActivity.class);
                    intent.putExtra("name", "Ivanov");
                    context.startActivity(intent);
                }
            });
        }

        public void bind(Teacher teacher) {
            name.setText(teacher.getName());
            phone.setText(teacher.getPhone());
            qualification.setText(teacher.getQualification());
        }
    }
}
