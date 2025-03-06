package template;

public class TemplateGoF {

    public static void main(String[] args) {
        new ResourceUser().execute();
        new ResourceEmployer().execute();
    }

    public static abstract class AbstractResourceManipulatorTemplate {
        protected Resource resource;

        private void openResource() {
            resource = new Resource();
        }

        protected abstract void doSomethingWithResource(); // extension point. We want it to be only overridden

        private void closeResource() {
            resource.close();
            resource = null;
        }

        public void execute() {
            openResource();
            try {
                doSomethingWithResource();
            } finally {
                closeResource(); // always close the resource (guarantee).
            }
        }
    }

    public static class ResourceUser extends AbstractResourceManipulatorTemplate {
        @Override
        protected void doSomethingWithResource() {
            resource.useResource();
        }
    }

    public static class ResourceEmployer extends AbstractResourceManipulatorTemplate {
        @Override
        protected void doSomethingWithResource() {
            resource.employResource();
        }
    }
}
