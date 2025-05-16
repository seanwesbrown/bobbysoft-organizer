import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { LoginForm } from '@vaadin/react-components';
import { useSearchParams } from 'react-router';

export const config: ViewConfig = {
  title: 'Login',
};

export default function LoginView() {
  const [searchParams] = useSearchParams();
  const hasError = searchParams.has('error');

  return (
    <main className="bobby-login-view w-full h-full flex flex-col box-border gap-s p-m">
      <LoginForm error={hasError} action="login" />
    </main>
  );
}
