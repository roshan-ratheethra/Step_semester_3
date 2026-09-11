public class BusRoute implements Comparable<BusRoute> {
    private static final int DEFAULT_PRIORITY = 2;

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, DEFAULT_PRIORITY);
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(BusRoute other) {
        if (other == null) return -1;

        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        int codeDiff = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeDiff != 0) {
            return codeDiff;
        }

        int lenDiff = this.routeName.length() - other.routeName.length();
        if (lenDiff != 0) {
            return lenDiff;
        }

        return this.routeName.compareTo(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        BusRoute[] arr = routes.clone();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    BusRoute temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        System.out.print("[");
        for (int i = 0; i < ranked.length; i++) {
            System.out.print(""" + ranked[i].getRouteCode() + """ + (i < ranked.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
