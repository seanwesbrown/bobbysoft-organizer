CREATE TABLE modules(
  id uuid DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL,
  name varchar(256) NOT NULL,
  x_position integer DEFAULT 0,
  y_position integer DEFAULT 0,
  width integer DEFAULT 0,
  height integer DEFAULT 0,
  primary_color_hex varchar(7),
  secondary_color_hex varchar(7)
);

CREATE TABLE module_components (
  id uuid DEFAULT gen_random_uuid(),
  module_id uuid NOT NULL,
  name varchar(256) NOT NULL,
  x_position integer DEFAULT 0,
  y_position integer DEFAULT 0,
  width integer DEFAULT 0,
  height integer DEFAULT 0,
  panel_mode boolean NOT NULL DEFAULT false,
  module_content json
);
