import java.util.ArrayList;
import java.util.List;

public class DivEConq {
    public static void main(String[] args) {
        var list = new ArrayList<>(List.of(5, 3, 8));

        var orderedList = mergeSort(list);
        for (Integer i : orderedList) {
            System.out.println(i);
        }
    }

    private static List<Integer> mergeSort(List<Integer> list) {
        /*
        *   IF (list L has one element)
            RETURN L.
            Divide the list into two halves A and B.
            A ← MERGE-SORT(A).
            B ← MERGE-SORT(B).
            L ← MERGE(A, B).
            RETURN L.
        * */

        if (list.size() == 1 || list.isEmpty()) {
            return list;
        }
        var half = list.size() / 2;
        var lower = mergeSort(list.subList(0, half));
        var upper = mergeSort(list.subList(half, list.size()));

        // ===
        var orderedList = new ArrayList<Integer>();


        return orderedList;
    }
}