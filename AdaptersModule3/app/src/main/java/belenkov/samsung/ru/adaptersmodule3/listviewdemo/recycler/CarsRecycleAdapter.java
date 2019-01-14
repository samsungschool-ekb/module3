package belenkov.samsung.ru.adaptersmodule3.listviewdemo.recycler;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import belenkov.samsung.ru.adaptersmodule3.R;
import belenkov.samsung.ru.adaptersmodule3.listviewdemo.Car;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CarsRecycleAdapter extends RecyclerView.Adapter<CarsRecycleAdapter.CarsVH> {

    List<Car> carList;

    public CarsRecycleAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.nice_car_item, viewGroup, false);

        return new CarsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsVH carsVH, final int i) {
        Car car = carList.get(i);

        carsVH.carImage.setImageResource(car.getImageResourseId());
        carsVH.carName.setText(car.getName());
        carsVH.carDescription.setText(car.getDescription());
        carsVH.carCard.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  carList.remove(i);
                                                  notifyItemRemoved(i);
                                                  notifyItemChanged(i);
                                              }
                                          }
        );
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class CarsVH extends RecyclerView.ViewHolder {
        private ImageView carImage;
        private TextView carName;
        private TextView carDescription;
        private CardView carCard;

        public CarsVH(@NonNull View itemView) {
            super(itemView);

            carImage = itemView.findViewById(R.id.carImage);
            carName = itemView.findViewById(R.id.carCardName);
            carDescription = itemView.findViewById(R.id.carCarDescription);
            carCard = itemView.findViewById(R.id.carCard);
        }
    }
}
