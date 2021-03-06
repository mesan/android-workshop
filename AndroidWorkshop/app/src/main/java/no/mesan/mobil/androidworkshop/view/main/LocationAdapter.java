package no.mesan.mobil.androidworkshop.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import no.mesan.mobil.androidworkshop.R;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private List<String> locations = new ArrayList<>();

    private LocationItemClickListener locationItemClickListener;

    public LocationAdapter(LinkedHashSet<String> locations, LocationItemClickListener locationItemClickListener) {
        this.locations = new ArrayList<>(locations);
        this.locationItemClickListener = locationItemClickListener;
    }

    public LinkedHashSet<String> getLocations() {
        return new LinkedHashSet<>(locations);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView textViewLocation;

        public ViewHolder(View view) {
            super(view);
            textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Oppgave 3e - Gå til listesiden ved klikk på et element i lista
            locationItemClickListener.onClick(textViewLocation.getText().toString());
        }
    }

    public void addLocation(String location) {
        locations.add(0, location);
        notifyItemInserted(0);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewLocation.setText(locations.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return locations.size();
    }
}
