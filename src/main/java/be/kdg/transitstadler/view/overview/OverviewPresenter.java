package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewPresenter {
    private TransitStadlerModel model;
    private OverviewView view;

    // TODO: documentation
    public OverviewPresenter(TransitStadlerModel model, OverviewView view) {
        this.model = model;
        this.view = view;
    }
}
