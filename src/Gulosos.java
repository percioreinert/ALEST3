import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Gulosos {
    public static void main(String[] args) {
        List<Event> lista = init();

        List<Event> orderedList = lista.stream()
                .sorted(Comparator.comparing(Event::getEnd))
                .toList();

        List<Event> chosen = new ArrayList<>();

        for (Event event : orderedList) {
            if (chosen.isEmpty()) {
                chosen.add(event);
            } else {
                if (event.start >= chosen.get(chosen.size() - 1).end) {
                    chosen.add(event);
                }
            }
        }

        System.out.println(chosen.size());
    }

    private static List<Event> init() {
        List<Event> lista = new ArrayList<>();
        lista.add(new Event(4, 8));
        lista.add(new Event(6, 7));
        lista.add(new Event(13, 14));
        lista.add(new Event(4, 5));
        lista.add(new Event(2, 4));
        lista.add(new Event(6, 9));
        lista.add(new Event(7, 10));
        lista.add(new Event(9, 11));
        lista.add(new Event(1, 6));
        lista.add(new Event(3, 13));
        lista.add(new Event(9, 12));
        return lista;
    }

    private static class Event {
        private int start;
        private int end;

        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getDifference() {
            return end - start;
        }

        @Override
        public String toString() {
            return "Start: " + start +
                    "\n" +
                    "End: " + end;
        }
    }
}