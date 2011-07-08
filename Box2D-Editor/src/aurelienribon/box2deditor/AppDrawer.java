package aurelienribon.box2deditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

public class AppDrawer {
	private static final Color SHAPE_LINE_COLOR = new Color(0.2f, 0.2f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color SHAPE_POLY_COLOR = new Color(0.2f, 0.8f, 0.2f, 1);
	private static final Color SHAPE_CENTER_COLOR = new Color(0.8f, 0.2f, 0.2f, 1);
	private static final Color MOUSEPATH_COLOR = new Color(0.2f, 0.2f, 0.2f, 1);

	private final OrthographicCamera camera;
	private final ImmediateModeRenderer imr;
	private final Vector2 tp1 = new Vector2();
	private final Vector2 tp2 = new Vector2();

	public AppDrawer(OrthographicCamera camera) {
		this.camera = camera;
		this.imr = new ImmediateModeRenderer();
	}

	public void draw() {
		Vector2 center = AppContext.instance().getTempCenter();
		Vector2[] shape = AppContext.instance().getTempShape();
		Vector2[][] polys = AppContext.instance().getCurrentBodyModel().getPolygons();

		if (AppContext.instance().arePolyDrawn && polys != null) {
			drawPolys(polys);
		}

		if (AppContext.instance().isShapeDrawn && center != null && shape != null) {
			drawShape(shape);
			drawPoints(shape);
			drawCenter(center);
		}

		drawMousePath();
	}

	private void drawShape(Vector2[] shape) {
		if (shape.length > 0) {
			for (int i=1; i<shape.length; i++)
				drawLine(shape[i], shape[i-1], SHAPE_LINE_COLOR, 2);

			if (AppContext.instance().isTempShapeClosed()) {
				drawLine(shape[0], shape[shape.length-1], SHAPE_LINE_COLOR, 2);
			} else {
				Vector2 p = AppContext.instance().getTempShapeNextPoint();
				drawLine(shape[shape.length-1], p, SHAPE_LASTLINE_COLOR, 2);
			}
		}
	}

	private void drawPoints(Vector2[] shape) {
		Vector2 np = AppContext.instance().getTempShapeNearestPoint();
		List<Vector2> sp = AppContext.instance().selectedPoints;
		float w = 10 * camera.zoom;

		for (Vector2 p : shape) {
			if (p == np || sp.contains(p))
				fillRect(p, w, w, SHAPE_LINE_COLOR);
			drawRect(p, w, w, SHAPE_LINE_COLOR, 2);
		}
	}

	private void drawPolys(Vector2[][] polys) {
		for (Vector2[] poly : polys) {
			for (int i=1; i<poly.length; i++)
				drawLine(poly[i], poly[i-1], SHAPE_POLY_COLOR, 2);
			if (poly.length > 0)
				drawLine(poly[0], poly[poly.length-1], SHAPE_POLY_COLOR, 2);
		}
	}

	private void drawCenter(Vector2 p) {
		Vector2 nearestP = AppContext.instance().getTempShapeNearestPoint();
		float w = 10 * camera.zoom;

		tp1.set(p).sub(w, 0);
		tp2.set(p).add(w, 0);
		drawLine(tp1, tp2, SHAPE_CENTER_COLOR, 2);

		tp1.set(p).sub(0, w);
		tp2.set(p).add(0, w);
		drawLine(tp1, tp2, SHAPE_CENTER_COLOR, 2);

		if (p == nearestP)
			fillRect(p, w, w, SHAPE_CENTER_COLOR);
		drawRect(p, w, w, SHAPE_CENTER_COLOR, 2);
	}

	private void drawMousePath() {
		List<Vector2> mp = AppContext.instance().mousePath;
		for (int i=1; i<mp.size(); i++)
			drawLine(mp.get(i), mp.get(i-1), MOUSEPATH_COLOR, 1);
		if (mp.size() > 1)
			drawLine(mp.get(0), mp.get(mp.size()-1), MOUSEPATH_COLOR, 1);
	}

	public void drawLine(Vector2 p1, Vector2 p2, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p1.x, p1.y, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p2.x, p2.y, 0);
		imr.end();
	}

	public void drawRect(Vector2 p, float w, float h, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINE_STRIP);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.end();
	}

	public void fillRect(Vector2 p, float w, float h, Color c) {
		imr.begin(GL10.GL_TRIANGLE_FAN);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y - h/2, 0);
		imr.end();
	}
}
