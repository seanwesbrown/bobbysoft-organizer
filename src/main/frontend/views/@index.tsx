import { useNavigate } from 'react-router';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, VerticalLayout } from '@vaadin/react-components';

export const config: ViewConfig = {
  menu: {
    exclude: true,
  },
};

export default function MainView() {
  const navigate = useNavigate();

  return (
    <main className="h-full">
      <div className="bobby-main-layout h-full">
        <div className="bobby-main-left p-m">
          <VerticalLayout className="bobby-title p-m">
            <h1>Bobbysoft Organizer</h1>
            <p>Welcome to Bobbysoft Organizer, the modular organizer.</p>
            <Button key="login" theme="primary" onClick={() => navigate('/login')}>
              Login
            </Button>
            <Button key="signup" onClick={() => navigate('/signup')}>
              Sign Up
            </Button>
          </VerticalLayout>
        </div>
        <VerticalLayout className="bobby-main-right" />
        <div className="bobby-background"></div>
      </div>
    </main>
  );
}
