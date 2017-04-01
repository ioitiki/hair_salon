import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      Stylist newStylist = new Stylist(name, description);
      newStylist.save();
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      int age = Integer.parseInt(request.queryParams("age"));
      String gender = request.queryParams("gender");
      Client newClient = new Client(name, id, description, age, gender);
      newClient.save();
      String url = "/stylists/" + id;
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/clients/:clientId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/about", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/about.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/clients/:clientId/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      int clientId = Integer.parseInt(request.params(":clientId"));
      Client client = Client.find(clientId);
      client.deleteClient();
      String url = "/stylists/" + id;
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/clients/:clientId/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientId = Integer.parseInt(request.params(":clientId"));
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(clientId);
      model.put("stylists", Stylist.all());
      model.put("currentStylist", stylist);
      model.put("client", client);
      model.put("template", "templates/edit-client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/clients/:clientId/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      String name = request.queryParams("name");
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      String description = request.queryParams("description");
      int age = Integer.parseInt(request.queryParams("age"));
      String gender = request.queryParams("gender");
      client.updateClient(name, stylistId, description, age, gender);
      String url = "/stylists/" + Integer.parseInt(request.params(":id"));
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Stylist stylist = Stylist.find(id);
      stylist.deleteStylist();
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/edit-stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      stylist.updateStylist(name, description);
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/search/:search", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String searchInput = request.params(":search");
      model.put("stylists", Stylist.searchStylists(searchInput));
      model.put("clients", Client.searchClients(searchInput));
      model.put("template", "templates/results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/search", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String searchInput = request.queryParams("searchInput");
      String url = String.format("/search/%s", searchInput);
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
